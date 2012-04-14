package auctionhaus

class ListingExpiredJob {
    def listingJMSEmailService

        def startDelay = 120000
        def timeout = 120000
        def group = "MyGroup"


    def execute() {

      listingJMSEmailService.expiredListing()

    }

}
