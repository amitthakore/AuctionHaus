package auctionhaus



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Bid)
class BidTests {


   // B-1: Bids have the following required fields: amount and date/time of bid (unit test)
    //check that null required bid fields cause an error (exceptional case)
    void testBidNullFieldsCauseError()
    {
        mockForConstraintsTests(Bid)
        def bidNull = new Bid()

        assert  !bidNull.validate()

        assert "nullable" == bidNull.errors["bidDateTime"]
        assert "nullable" == bidNull.errors["bidAmount"]


    }

    // B-1: Bids have the following required fields: amount and date/time of bid (unit test)
    //check that there is no nullable error when the required fields are present (happy path)
    void testBidNonNullFieldsOK()
    {
        mockForConstraintsTests(Bid)


        BigDecimal startingBidPrice = 10.00
        // Create a test end date and time that is one day from today
        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(email:"amit_thakore1@yahoo.com",password: "1234567",createddate: new Date())

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller)

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: testSeller, listing:testList )
        
        
       assert  bidTest.validate()

        assert bidTest.errors["bidDatetime"]   != "nullable"
        assert bidTest.errors["bidAmount"]   != "nullable"


    }

    //B-2: Bids are required to be for a Listing (unit test)
    //check that null required listing causes an error (exceptional case)
    void testBidListingNullCausesError()
    {

        mockForConstraintsTests(Bid)
        def bidNull = new Bid()

        assert  !bidNull.validate()


        assert "nullable" == bidNull.errors["listing"]
    }

    //B-2: Bids are required to be for a Listing (unit test)
    //check that no nullable error when listing is present (exceptional case)
    void testBidListingNonNullOK()
    {
        mockForConstraintsTests(Bid)


        BigDecimal startingBidPrice = 10.00
        // Create a test end date and time that is one day from today
        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(email:"amit_thakore1@yahoo.com",password: "1234567",createddate: new Date())

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller)

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: testSeller, listing:testList )

        assert bidTest.validate()


        assert "nullable" != bidTest.errors["listing"]

    }

  //  B-3: Bids are required to have a bidder (Customer) (unit test)
  //check that null required bidder causes an error (exceptional case)
    void testBidBidderNullCausesError()
    {
        mockForConstraintsTests(Bid)
        def bidNull = new Bid()

        assert  !bidNull.validate()


        assert "nullable" == bidNull.errors["bidder"]



        
    }

    //  B-3: Bids are required to have a bidder (Customer) (unit test)
    //check that no nullable error when the bidder is present (happy path)
    void testBidBidderNonNullOK()
    {
        mockForConstraintsTests(Bid)


        BigDecimal startingBidPrice = 10.00
        // Create a test end date and time that is one day from today
        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(email:"amit_thakore1@yahoo.com",password: "1234567",createddate: new Date())

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller)

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: testSeller, listing:testList )

        assert bidTest.validate()


        assert "nullable" != bidTest.errors["bidder"]




    }

  //  B-4: A Listing has a list of Bids for that Listing (unit test)
    //check that I can add a bid to the list of bids for the Listing (happy path)
    void testListingHasListOfBids()
    {



        mockForConstraintsTests(Listing)

        BigDecimal startingBidPrice = 10.00

        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(email:"amit_thakore1@yahoo.com",password: "1234567",createddate: new Date())

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller)

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: testSeller, listing:testList )

        
        mockDomain(Listing,[testList])

        testList.addToBids(bidTest)


        assert testList.validate()

        assert "validator" != testList.errors["bids"]
        
    }


}
