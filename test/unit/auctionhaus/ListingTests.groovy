package auctionhaus



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Listing)
class ListingTests {

    void testConstrains() {
     mockDomain(Listing)
     mockDomain(Customer)
       fail "Implement me"
    }
}
