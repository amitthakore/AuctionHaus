package auctionhaus

class CreateBidService {
 //SRV-3 Create a grails service method for creating Bid
    def createNewBid(Bid bidInstance) {
        bidInstance.bidDateTime = new Date()
        if (bidInstance.listing.listingEndDateTime > new Date()){
        if (!bidInstance.hasErrors() && bidInstance.save())
        {

        } else  {

            throw new grails.validation.ValidationException("Bid Error", bidInstance.errors)

        }

        }
        else {

            throw new grails.validation.ValidationException("Listing Expired", bidInstance.errors)
            }

        return bidInstance
    }
}


