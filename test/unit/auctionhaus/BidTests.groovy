package auctionhaus



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Bid)
class BidTests {


   // B-1: Bids have the following required fields: amount and date/time of bid (unit test)
    void testBidNullFieldsCauseError()
    {                                                                                                                                                 mockForConstraintsTests(Customer)
        mockForConstraintsTests(Bid)
        def bidNull = new Bid()

        assert  !bidNull.validate()

        assert "nullable" == bidNull.errors["bidDatetime"]
        assert "nullable" == bidNull.errors["bidAmount"]


    }

    // B-1: Bids have the following required fields: amount and date/time of bid (unit test)
    void testBidNonNullFieldsOK()
    {                                                                                                                                                 mockForConstraintsTests(Customer)
        mockForConstraintsTests(Bid)
        def bidTest = new Bid()

        bidTest.bidDatetime = new Date(5000)
        bidTest.bidAmount = 2

        bidTest.validate()

        assert bidTest.errors["bidDatetime"]   != "nullable"
        assert bidTest.errors["bidAmount"]   != "nullable"


    }


  //  B-3: Bids are required to have a bidder (Customer) (unit test)
    
    void testBidListingNullCausesError()
    {
        mockForConstraintsTests(Bid)
        def bidNull = new Bid()

        assert  !bidNull.validate()


        assert "nullable" == bidNull.errors["bidder"]



        
    }

    //  B-3: Bids are required to have a bidder (Customer) (unit test)

    void testBidListingNonNullOK()
    {
        mockForConstraintsTests(Bid)
        def bidTest = new Bid()
        
        bidTest.bidder = new Customer(email:"amit_thakore1@yahoo.com",password: "1234567",createddate: new Date())

        bidTest.validate()


        assert "nullable" != bidTest.errors["bidder"]




    }



}
