package auctionhaus

import static org.junit.Assert.*
import org.junit.*
import org.springframework.test.annotation.ExpectedException
import grails.validation.ValidationException

//Amit Thakore

class BidIntegrationTests extends GroovyTestCase {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }


    // B-5: The Bid amount must be at least .50 higher than the previous Bid for the same listing (integration test)
    //Test that adding a bid that is not 0.50 higher than previous bid causes validation exception on save. (exceptional case)
    @Test
    void testBidNotHighEnoughCausesError()
    {


        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(username: "amit_thakore1@yahoo.com",password: "1234567",createdDate: new Date(),enabled: true)

        def bidTest = new Bid(bidAmount: 20.0, bidDateTime: new Date(), bidder: testSeller)


        def bidTest2 = new Bid(bidAmount: 20.4, bidDateTime: new Date(), bidder: testSeller )


        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller,listingCreatedDate: new Date())
        testSeller.save()

        testList.save()

        testList.addToBids(bidTest)


        testList.addToBids(bidTest2)
        //since bidTest2 is only 0.40 greater than the last bid, it should cause a validation error

        shouldFail(ValidationException){
            bidTest2.save()

        }

    }


    // B-5: The Bid amount must be at least .50 higher than the previous Bid for the same listing (integration test)
    //Test that adding a bid that is 0.50 higher than previous bid  successfully validates, no error on bidAmount field. (happy path)
    @Test
    void testBidHighEnoughOK()
    {


        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(username: "amit_thakore1@yahoo.com",password: "1234567",createdDate: new Date())
        testSeller.save(flush: true)
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller,listingCreatedDate: new Date())
        testList.save(flush: true)

        def bidTest = new Bid(bidAmount: 20.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)

        bidTest.save(flush: true)
        def bidTest2 = new Bid(bidAmount: 20.5, bidDateTime: new Date()+2, bidder: testSeller,listing: testList )





        //since bidTest2 is 0.50 greater than the last bid, it should not cause a validation error
        bidTest2.save(flush: true)
        assert bidTest2.validate()

        assert bidTest2.errors['bidAmount'] == null



    }


    // B-6: When a Listing is saved, any new Bids added for the listing must be saved (integration test)
    // Test that bid is saved to db when added to list and list is saved (happy path)
    @Test
    void testListingSavedBidsAlsoSaved()
    {

        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(username: "amit_thakore1@yahoo.com",password: "1234567",createdDate: new Date())
         testSeller.save(flush: true)
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller,listingCreatedDate: new Date())
         testList.save(flush: true)
        def bidTest = new Bid(bidAmount: 25.0, bidDateTime: new Date()+1, bidder: testSeller,listing: testList)

         bidTest.save(flush: true)

       // def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller)


       // testList.addToBids(bidTest)



        //testList.save()


        //we should find bidTest in the db, because it was added to testList before call to testList.save
        assert (Bid.find(bidTest))




    }

    // B-6: When a Listing is saved, any new Bids added for the listing must be saved (integration test)
    // Test that bid is not saved to db when not added to list and list is saved (excaptional path)
    @Test
    void testListingNotSavedBidsAlsoNotSaved()
    {


        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(username: "amit_thakore1@yahoo.com",password: "1234567",createdDate: new Date())

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller,listingCreatedDate: new Date())

        def bidTest = new Bid(bidAmount: 20.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)

        testSeller.save()

        //def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller)


        //    testList.addToBids(bidTest)



        testList.save()


        //we should find not bidTest in the db, because it was never saved nor added to testList before call to testList.save
        assert (!Bid.find(bidTest))

    }

    void testTopTenBidsForListing()
    {

        def testEndDateTime = new Date() + 1
        def testSeller = new Customer(username: "amit_thakore1@yahoo.com",password: "1234567",createdDate: new Date())
        testSeller.save()
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testSeller,listingCreatedDate: new Date())
        testList.save()

        def bidTest1 = new Bid(bidAmount: 20.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest2 = new Bid(bidAmount: 21.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest3 = new Bid(bidAmount: 22.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest4 = new Bid(bidAmount: 23.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest5 = new Bid(bidAmount: 24.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest6 = new Bid(bidAmount: 25.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest7 = new Bid(bidAmount: 26.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest8 = new Bid(bidAmount: 27.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest9 = new Bid(bidAmount: 28.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest10 = new Bid(bidAmount: 29.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)
        def bidTest11 = new Bid(bidAmount: 30.0, bidDateTime: new Date(), bidder: testSeller,listing: testList)

        bidTest1.save()
        bidTest2.save()
        bidTest3.save()
        bidTest4.save()
        bidTest5.save()
        bidTest6.save()
        bidTest7.save()
        bidTest8.save()
        bidTest9.save()
        bidTest10.save()
        bidTest11.save()
        def listingInstanceList  = Bid.topBids(testList.id).list(max:10)
      // check that query is returning 10
        assert (listingInstanceList.size()==10)
        // check that query is returning last 10
        assert listingInstanceList[0].bidAmount==30
        assert listingInstanceList[9].bidAmount== 21
    }

}
