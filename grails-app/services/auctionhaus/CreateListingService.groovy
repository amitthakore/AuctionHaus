package auctionhaus
import grails.plugins.springsecurity.*

class CreateListingService {
def springSecurityService

Listing createNewListing(Listing listingInstance){

 if (!listingInstance.hasErrors() && listingInstance.save())
        {

    } else  {
     throw new grails.validation.ValidationException("listing Error", listingInstance.errors)

    }
        return listingInstance
}
}