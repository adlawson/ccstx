package com.fastrailticketing.ccstprinting

import org.scalatest.{FlatSpecLike, Matchers}
import scala.concurrent.Future

class PrintJobsAPISoapBindingsSpec extends FlatSpecLike with Matchers {

  // Create a concrete instance with all required definitions
  object PrintClient extends IPrintJobs {
    override def createJob(job: CreateJobReq): Future[CreateJobResponse] = ???
    override def createRemoteJob(job: CreateRemoteJobReq): Future[CreateRemoteJobResponse] = ???
    override def getTicketStocks(): Future[GetTicketStocksResponse] = ???
    override def getDeliveryChannels(): Future[GetDeliveryChannelsResponse] = ???
    override def getDeliveryCountries(): Future[GetDeliveryCountriesResponse] = ???
    override def getDeliveryMethods(countryCode: String): Future[GetDeliveryMethodsResponse] = ???
    override def getRemoteSpoolers(): Future[GetRemoteSpoolersResponse] = ???
  }

  // This is obviously true if the above compiles
  it should "be an instance of IPrintJobs" in {
    PrintClient shouldBe a[IPrintJobs]
  }
}
