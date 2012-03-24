package auctionhaus



import org.junit.*
import grails.test.mixin.*

@TestFor(BidController)
@Mock([Bid,Customer,Listing])
class BidControllerTests {

 //L-7 new bid can be placed for a listing
    void testBidSave() {

        def testEndDateTime = new Date() + 1

        def customer = new Customer(email:"unique1@yahoo.com",password: "1234567",createdDate: new Date())

        customer.save()

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customer,listingCreatedDate:new Date())
        testList.save()

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: customer, listing:testList)

        bidTest.save()
        params.id = bidTest.id;
        controller.save()
        assert Bid.count() == 1

    }

    void testBidSaveError() {

        def testEndDateTime = new Date() + 1

        def customer = new Customer(email:"unique1@yahoo.com",password: "1234567",createdDate: new Date())

        customer.save()

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customer,listingCreatedDate:new Date())
        testList.save()

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: customer, listing:testList)

        bidTest.save()
        params.id = bidTest.id;
        controller.save()
        assert Bid.count() == 1

        def bidTest1 = new Bid(bidAmount: 20.4, bidDateTime: new Date(), bidder: customer, listing:testList)
        bidTest1.save()
        params.id = bidTest1.id;
        controller.save()

        assert Bid.count() == 1

        assert flash.message == 'bid.amount.not.valid'
    }


}