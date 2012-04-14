package auctionhaus



import org.junit.*
import grails.test.mixin.*


@TestFor(CustomerController)
@Mock([Customer,Listing,Bid,Role])

class CustomerControllerTests {

    //C-3: Verify that Customer can be deleted in there is no active bids and listings (Happy path)

    //C-4: Verify that Customer can be not be deleted if there are active bids ( path)
  void testCustomerDeleteErrorActiveBids()
    {
       response.reset()
       mockForConstraintsTests(Customer)
       mockForConstraintsTests(Bid)
       // mockForConstraintsTests(Listing)
        mockFor(Customer)
        def testEndDateTime = new Date() + 1

        def customerDeleteError = new Customer(username: "unique1@yahoo.com",password: "1234567",createdDate: new Date(),enabled:true)
        Customer.metaClass.encodePassword = { -> }
        customerDeleteError.save()

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customerDeleteError,listingCreatedDate:new Date())
        testList.save()

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: customerDeleteError, listing:testList)

        bidTest.save()


        assert Customer.count() == 1
        params.id =  bidTest.bidder.id

        controller.delete()

        assert Customer.count() == 1

        assert Customer.get(customerDeleteError.id) != null


    }


    //C-4: Verify that Customer can be not be deleted if there are active listings ( path)
    void testCustomerDeleteErrorActiveListings()
    {
        response.reset()
        mockForConstraintsTests(Customer)
        mockForConstraintsTests(Bid)
        // mockForConstraintsTests(Listing)
        mockFor(Customer)
        def testEndDateTime = new Date() + 1

        def customerDeleteError = new Customer(username: "unique2@yahoo.com",password: "1234567",createdDate: new Date(),enabled: true)
        Customer.metaClass.encodePassword = { -> }
        customerDeleteError.save()

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customerDeleteError,listingCreatedDate:new Date())
        testList.save()

        assert Customer.count() == 1
        params.id =  customerDeleteError.id

        controller.delete()

        assert Customer.count() == 1

        assert Customer.get(customerDeleteError.id) != null


    }
}
