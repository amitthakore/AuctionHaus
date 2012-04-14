package auctionhaus

import static org.junit.Assert.*
import org.junit.*
import grails.plugin.jms.Queue
import com.icegreen.greenmail.util.*
import grails.converters.JSON

class ListingJMSEmailIntegrationTests {
    def listingJMSEmailService
     def listeningService
     def greenMail
    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testJMSService() {

        def  testEndDateTime = new Date() + 1
        def testCustomer = new Customer(username: "unique1@yahoo.com",password: "1234567",createdDate: new Date(),enabled: true)
        Customer.metaClass.encodePassword = { -> }
        testCustomer.save()
        // add listing
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testCustomer,listingCreatedDate:new Date())
         testList.save()
        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: testCustomer, listing:testList )
         bidTest.save()

        def listingCreateService = new CreateListingService()

        def listingDetails = new JSON(bidTest).toString()
        listingJMSEmailService.sendJMSMessage(listingDetails)
        listeningService.receive()
        assert listeningService.QUEUE_NAME != []

    }


    void testSendMail() {


        def listingCreateService = new CreateListingService()

        def  testEndDateTime = new Date() -1

        def testCustomer = new Customer(username: "amitthakore16@gmail.com",password: "1234567",createdDate: new Date(),enabled: true)

        Customer.metaClass.encodePassword = { -> }
        testCustomer.save()
        // add listing
        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testCustomer,listingCreatedDate:new Date())

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: testCustomer, listing:testList )

        listingJMSEmailService.sendEmail(bidTest,testList)

        assertEquals(1, greenMail.getReceivedMessages().length)

    }

}
