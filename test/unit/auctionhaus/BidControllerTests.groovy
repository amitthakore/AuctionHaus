package auctionhaus

import org.junit.*
import grails.test.mixin.*

@TestFor(BidController)
@Mock([Bid,Customer,Listing])
class BidControllerTests {

 //L-7 L-7: The detail page for the listing allows a new bid to be placed (unit test of the controller action that handles this requirement)
  void testBidSave() {

        def testEndDateTime = new Date() + 1
        //add customer
        def customer = new Customer(username: "unique1@yahoo.com",password: "1234567",createdDate: new Date(),enabled: true)
      Customer.metaClass.encodePassword = { -> }
      customer.save()
       // add listing
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customer,listingCreatedDate:new Date())
        testList.save()
      //set parameters to save bid
      params["bidAmount"] = 20

      params["bidder"] = customer

      params["listing"] = testList

      request.method = "POST"
      controller.save()

        assert Bid.count() == 1
      assert flash.message == 'bid.created.message'

    }
   //L-8: Validation errors will be displayed on the listing detail page if an added bid does not pass validation (unit test of the controller action that handles this requirement)
   void testBidSaveError() {

        def testEndDateTime = new Date() + 1
        // add customer
        def customer = new Customer(username: "unique1@yahoo.com",password: "1234567",createdDate: new Date())
       Customer.metaClass.encodePassword = { -> }
       customer.save()

        //add list
       def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customer,listingCreatedDate:new Date())

       testList.save()

        // save parameters to save bid
       params["bidAmount"] = 20

       params["bidder"] = customer

       params["listing"] = testList

       request.method = "POST"
       controller.save()


        assert Bid.count() == 1

        // add another bid with wrong bid amount

       params["bidAmount"] = 20.4

       params["bidder"] = customer

       params["listing"] = testList

       request.method = "POST"
       controller.save()

        assert Bid.count() == 1

        assert flash.message == 'bid.amount.not.valid'




    }


}