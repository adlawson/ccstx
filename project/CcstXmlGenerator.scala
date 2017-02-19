package io.otrl.ccstx.project

import sbt._
import treehugger.forest._
import treehugger.forest.definitions._
import treehugger.forest.treehuggerDSL._
import java.io.File
import scala.xml.{Node, NodeSeq}

object CcstXmlGenerator {

  def gen(dir: File, root: Node, packageName: String): Seq[File] =
    CcstXmlLiteralGenerator.gen(dir, root, packageName) ++
    CcstXmlFormatGenerator.gen(dir, root, packageName) ++
    CcstXmlFieldGenerator.gen(dir, root, packageName) ++
    CcstVersionGenerator.gen(dir, root, packageName)
}

// Common operations for parser instances
sealed trait CcstXmlGeneratorOps {

  val rootPackageName: String = "io.otrl.ccstx.rsps3018"

  def fieldIdDefName(id: String): String =
     "field" + formatId(id)

  def formatId(id: String): String =
     id.replaceAll("^0+", "").replace("_", "")

  def literalIdDefName(id: String): String =
    "literal" + formatId(id)

  def genImports(packageName: String): Tree =
    if (packageName == rootPackageName) EmptyTree // Avoids unused imports
    else IMPORT(rootPackageName, "_")
}

// CCSTX format types generator
object CcstXmlFormatGenerator extends CcstXmlGeneratorOps {

  def gen(dir: File, root: Node, packageName: String): Seq[File] = {
    val file = dir / "formats.scala"
    IO.write(file, treeToString(PACKAGE(packageName) := BLOCK {
      genImports(packageName) +: genFormats(root)
    }))
    file :: Nil
  }

  def genBounds(node: Node): Tree = {
    val tlx = LIT((node \ "X_TOP_LEFT").text.toInt)
    val tly = LIT((node \ "Y_TOP_LEFT").text.toInt)
    val brx = LIT((node \ "X_BOTTOM_RIGHT").text.toInt)
    val bry = LIT((node \ "Y_BOTTOM_RIGHT").text.toInt)
    REF("Bounds") APPLY((REF("Coords") APPLY(tlx, tly)), (REF("Coords") APPLY(brx, bry)))
  }

  def genBoxes(root: Node): Seq[Tree] = Seq(DEF("boxes") := LIST((root \ "FORMAT_BOXES" \ "BOX").map { node =>
    REF("Box") APPLY(genBounds(node))
  }))

  def genDefs(id: String, root: Node): Seq[Tree] =
    (DEF("id") := REF("FormatId") APPLY(LIT(id))) +:
    (if ((root \ "ORANGE_COMMON_STOCK").text == "YES") Seq(DEF("stock") := REF("Stock.OrangeStandard")) else Nil)

  def genFieldDefs(root: Node): Seq[Tree] = distinctIdNodes(root \ "FORMAT_FIELDS" \ "FIELD").map { node =>
    val id = node.attribute("ID").get.text
    DEF(fieldIdDefName(id), "PartialField")
  }

  def genFields(root: Node): Seq[Tree] = Seq(DEF("fields") := LIST((root \ "FORMAT_FIELDS" \ "FIELD").map { node =>
    val id = node.attribute("ID").get.text
    REF(fieldIdDefName(id)) APPLY(genFont(node), genBounds(node))
  }))

  def genFont(node: Node): Tree = {
    val size = REF("FontSize") APPLY(LIT((node \ "SIZE").text.toInt))
    val align = REF("Alignment." + (if((node \ "ALIGN").text == "L") "Left" else "Right"))
    val emphasis = REF("Emphasis." + emphasisNameFromId((node \ "EMPHASIS").text.toInt))
    REF("Font") APPLY(size, align, emphasis)
  }

  def genFormats(root: Node): Seq[Tree] = (root \ "CCST_X" \ "FORMATS" \ "FORMAT").map { node =>
    val id = node.attribute("ID").get.text
    val traitName = formatId(id) + "Format"
    (TRAITDEF(traitName).withParents("Format") := BLOCK {
      genDefs(id, node) ++
      genBoxes(node) ++
      genFields(node) ++
      genLiterals(node) ++
      genFieldDefs(node)
    }).withDoc(s"$id ticket format", node \ "NOTES" text)
  }

  def genLiterals(root: Node): Seq[Tree] = Seq(DEF("literals") := LIST((root \ "FORMAT_LITERALS" \ "LITERAL").map { node =>
    val id = node.attribute("ID").get.text
    val defName = literalIdDefName(id)
    REF(s"Literals.$defName") APPLY(genFont(node), genBounds(node))
  }))

  def distinctIdNodes(nodes: NodeSeq): Seq[Node] = nodes.foldLeft(Map.empty[String, Node]) { case (out, node) =>
    var id = node.attribute("ID").get.text
    out.get(id) match {
      case Some(_) => out
      case None => out + (id -> node)
    }
  }.values.toSeq.sortBy(_.attribute("ID").get.text)

  def emphasisNameFromId(id: Int): String = id match {
    case  0 => "Regular"
    case  1 => "RegularUnderline"
    case  2 => "RegularItalic"
    case  3 => "RegularUnderlineItalic"
    case  4 => "RegularBold"
    case  5 => "RegularBoldUnderline"
    case  6 => "RegularBoldItalic"
    case  7 => "RegularBoldUnderlineItalic"
    case  8 => "Inverted"
    case  9 => "InvertedUnderline"
    case 10 => "InvertedItalic"
    case 11 => "InvertedUnderlineItalic"
    case 12 => "InvertedBold"
    case 13 => "InvertedBoldUnderline"
    case 14 => "InvertedBoldItalic"
    case 15 => "InvertedBoldUnderlineItalic"
  }
}

// CCSTX field definitions generator
object CcstXmlFieldGenerator extends CcstXmlGeneratorOps {

  def gen(dir: File, root: Node, packageName: String): Seq[File] = {
    val file = dir / "Fields.scala"
    IO.write(file, treeToString(PACKAGE(packageName) := BLOCK(
      genImports(packageName),
      OBJECTDEF("Fields").withParents("Fields"),
      TRAITDEF("Fields") := BLOCK(genDefs(root))
    )))
    file :: Nil
  }

  def genDefs(root: Node): Seq[Tree] = (root \ "CCST_X" \ "FIELDS" \ "FIELD").map { node: Node =>
    val id = node.attribute("ID").get.text
    val applied = REF("Field") APPLY((REF("FieldId") APPLY(LIT(id))), REF("value"), WILDCARD, WILDCARD)
    ((DEF(fieldIdDefName(id), "PartialField").withParams(PARAM("value", StringClass))) := applied).withDoc(
      s"Field $id",
      (node \ "DATA_ITEM").text
    )
  }
}

// CCSTX literal definitions generator
object CcstXmlLiteralGenerator extends CcstXmlGeneratorOps {

  def gen(dir: File, root: Node, packageName: String): Seq[File] = {
    val file = dir / "Literals.scala"
    IO.write(file, treeToString(PACKAGE(packageName) := BLOCK(
      genImports(packageName),
      OBJECTDEF("Literals").withParents("Literals"),
      TRAITDEF("Literals") := BLOCK(genDefs(root))
    )))
    file :: Nil
  }

  def genDefs(root: Node): Seq[Tree] = (root \ "CCST_X" \ "LITERALS" \ "LITERAL").map { node: Node =>
    val id = node.attribute("ID").get.text
    val value = (node \ "TEXT").text.replaceAll("'", "\u2019") // Replace `'` with `’` because ATOC font is crap
    val applied = REF("Literal") APPLY((REF("LiteralId") APPLY(LIT(id))), LIT(value), WILDCARD, WILDCARD)
    (DEF(literalIdDefName(id), "PartialLiteral") := applied).withDoc(s"Literal $id", value)
  }
}

// CCSTX version information generator
object CcstVersionGenerator extends CcstXmlGeneratorOps {

  def gen(dir: File, root: Node, packageName: String): Seq[File] = {
    val file = dir / "Version.scala"
    val version = (root \ "CCST_X").head.attribute("version").get.text
    val generated = (root \ "XML_GENERATED").head.text
    IO.write(file, treeToString(PACKAGE(packageName) := BLOCK(
      (CASEOBJECTDEF("Version").withParents("AnyRef") := BLOCK(VAL("value", StringClass) := LIT(version))).withDoc(
        s"RSPS3018 CCSTX version $version generated $generated" +: "\n" +:
        (root \ "CCST_X" \ "NOTES").head.text.split("\n")
      )
    )))
    file :: Nil
  }
}
