package auctionhaus

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CreateBidService)
@Mock([Bid,Listing,Customer])
class CreateBidServiceTests {

        //L-7 L-7: The detail page for the listing allows a new bid to be placed (unit test of the controller action that handles this requirement)
        void testBidSave() {

            def testEndDateTime = new Date() + 1
            //add customer
            def testCustomer = new Customer(username: "unique1@yahoo.com",password: "1234567",createdDate: new Date(),enabled: true)
            Customer.metaClass.encodePassword = { -> }
            testCustomer.save()
            // add listing
            def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testCustomer,listingCreatedDate:new Date())
            testList.save()
            // add bid
            def bidTest = new Bid()
            bidTest.bidAmount = 20
            bidTest.bidder = testCustomer
            bidTest.listing = testList

            def bidCreateService = new CreateBidService()

            bidCreateService.createNewBid(bidTest)

            assert Bid.count() == 1

        }

    //L-7 L-7: The detail page for the listing allows a new bid to be placed (unit test of the controller action that handles this requirement)
    void testBidErrorListingExpired() {
        try{
        def testEndDateTime = new Date() - 1
        //add customer
        def testCustomer = new Customer(username: "unique1@yahoo.com",password: "1234567",createdDate: new Date(),enabled: true)
        Customer.metaClass.encodePassword = { -> }
        testCustomer.save()
        // add listing
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testCustomer,listingCreatedDate:new Date())
        testList.save()
        // add bid
        def bidTest = new Bid()
        bidTest.bidAmount = 20
        bidTest.bidder = testCustomer
        bidTest.listing = testList

        def bidCreateService = new CreateBidService()

        bidCreateService.createNewBid(bidTest)
        // bid not created


    }
        catch(grails.validation.ValidationException e){
          //listing expired bid not created
            assert Bid.count() == 0
        }
    }


}
