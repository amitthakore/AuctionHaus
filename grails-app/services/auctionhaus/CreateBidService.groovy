package auctionhaus

class CreateBidService {

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
                throw new grails.validation.ValidationException("Listing Expired Bid can not be added", bidInstance.errors)
            }

        return bidInstance
    }
}


