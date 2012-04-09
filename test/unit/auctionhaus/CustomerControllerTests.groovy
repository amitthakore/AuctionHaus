package auctionhaus



import org.junit.*
import grails.test.mixin.*


@TestFor(CustomerController)
@Mock([Customer,Listing,Bid,Role])

class CustomerControllerTests {

    //C-3: Verify that Customer can be deleted in there is no active bids and listings (Happy path)
  /*void testCustomerDeleteOK()
    {

        def customerDeleteOK = new Customer(email:"athakore1@yahoo.com",password: "1234567",createdDate: new Date())

        customerDeleteOK.save(flush: true)


        params.id = customerDeleteOK.id
        controller.delete()

        assert Customer.count() == 0
        assert Customer.get(customerDeleteOK.id) == null
        assert response.redirectedUrl == '/customer/list'
    }   */


    void testCustomerOK()
    {

        def customerOK = new Customer(email:"athakore1@yahoo.com",password: "1234567",createdDate: new Date())
        customerOK.enabled = 'yes'
        customerOK.save()
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        CustomerRole.create customerOK, userRole ,true

    }

    //C-4: Verify that Customer can be not be deleted if there are active bids ( path)
 /*   void testCustomerDeleteErrorActiveBids()
    {
        response.reset()
       mockForConstraintsTests(Customer)
       mockForConstraintsTests(Bid)
       // mockForConstraintsTests(Listing)
        mockFor(Customer)
        def testEndDateTime = new Date() + 1

        def customerDeleteError = new Customer(email:"unique1@yahoo.com",password: "1234567",createdDate: new Date())

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


    }   */


    //C-4: Verify that Customer can be not be deleted if there are active listings ( path)
    void testCustomerDeleteErrorActiveListings()
    {
        response.reset()
        mockForConstraintsTests(Customer)
        mockForConstraintsTests(Bid)
        // mockForConstraintsTests(Listing)
        mockFor(Customer)
        def testEndDateTime = new Date() + 1

        def customerDeleteError = new Customer(email:"unique2@yahoo.com",password: "1234567",createdDate: new Date())

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
