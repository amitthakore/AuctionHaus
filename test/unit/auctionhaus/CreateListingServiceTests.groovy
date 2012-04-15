package auctionhaus

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
//SRV-2 Tests
@TestFor(CreateListingService)
@Mock([Listing,Customer])
class CreateListingServiceTests {

    void testListingOK() {
    def testEndDateTime = new Date() + 1
    //add customer
    def testCustomer = new Customer(username: "unique1@yahoo.com",password: "1234567",createdDate: new Date(),enabled: true)
    Customer.metaClass.encodePassword = { -> }
    testCustomer.save()
    // add listing
    def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:testCustomer,listingCreatedDate:new Date())

    def listingCreateService = new CreateListingService()

    listingCreateService.createNewListing(testList)

    assert testList.validate()


}


}