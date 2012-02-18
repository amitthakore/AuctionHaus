package auctionhaus



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Listing)
class ListingTests {

     void testConstrains() {
       mockForConstraintsTests(Listing)
       mockForConstraintsTests(Customer)
        
        def today = new Date()
        BigDecimal startingBidPrice = 10.00
        // Create a test end date and time that is one day from today
        def testEndDateTime = today + 1
        def testSeller = new Customer(email:"amit_thakore1@yahoo.com",password: "1234567",createddate: new Date())
       // def customeremail = new Customer(email:"amitthakore17@gmail.com",password: "1123456",createddate: new Date()).save()
//
        assert testSeller.validate()
        // Create a listing with name, end datetime, and starting bid price
      /*  def listing = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime,StartingBidPrice: 10.00, seller:testSeller)

        listing.save()
       // L-1: Listings have the following required fields: name, end date/time, and starting bid price (unit test)

        assert listing.validate()

        assert "nullable" != listing.errors["listingName"]
        assert "nullable" != listing.errors["listingEndDateTime"]
        assert "nullable" != listing.errors["startingBidPrice"]
       //L-2: Listings have the following optional fields: description (unit test)
        assert "nullable" == listing.errors["listingDescription"]
       // L-2 Save listing description
       listing.listingDescription = "Apple TV for Sale"
       listing.save()
       assert "nullable" != listing.errors["listingDescription"]
       assert listing.listingDescription == "Apple TV for Sale"
    // L-3: Listings are required to have a seller (Customer) (unit test)
      testSeller.validate()
      assert "nullable" != listing.errors["seller"]
      assert testSeller.email == "amit_thakore16@yahoo.com"
      assert testSeller.password == "1234567"
      assert testSeller.createddate == new Date()

    // L-4 Listing descriptions must be less than 256 characters

         //assert "size" != listing.errors["listingDescription"]
         //listing.listingDescription = '''xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'''
   
     //listing.save()
   //println(listing.listingDescription)         */
     }
}
