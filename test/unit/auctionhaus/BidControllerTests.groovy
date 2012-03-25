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
        def customer = new Customer(email:"unique1@yahoo.com",password: "1234567",createdDate: new Date())

        customer.save()
       // add listing
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customer,listingCreatedDate:new Date())
        testList.save()
       // add bid
        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: customer, listing:testList)

        bidTest.save()

        params.id = bidTest.id;

        controller.save()

        assert Bid.count() == 1

    }
   //L-8: Validation errors will be displayed on the listing detail page if an added bid does not pass validation (unit test of the controller action that handles this requirement)
    void testBidSaveError() {

        def testEndDateTime = new Date() + 1
        // add customer
        def customer = new Customer(email:"unique1@yahoo.com",password: "1234567",createdDate: new Date())

        customer.save()
        //add list
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customer,listingCreatedDate:new Date())

        testList.save()
        // add bid
        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: customer, listing:testList)

        bidTest.save()

        params.id = bidTest.id;
       // call controller's save method
        controller.save()

        assert Bid.count() == 1

        // add another bid with wrong bid amount
        def bidTest1 = new Bid(bidAmount: 20.4, bidDateTime: new Date(), bidder: customer, listing:testList)

        bidTest1.save()

        params.id = bidTest1.id;

        controller.save()

        assert Bid.count() == 1

        assert flash.message == 'bid.amount.not.valid'
    }


}