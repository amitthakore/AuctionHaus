package auctionhaus

class MaxBidForListingTagLib {
   //L-3: The detail page for the listing shows the most recent bid
    def maxBidForListingTagLib = { attrs ->
        if (attrs.listingInstance){

            out << """<span class="listingInstance ${attrs.listingInstance.bids.bidAmount.max()}">${attrs.listingInstance.bids.bidAmount.max().encodeAsHTML()}</span>"""
        }



        }

    }


