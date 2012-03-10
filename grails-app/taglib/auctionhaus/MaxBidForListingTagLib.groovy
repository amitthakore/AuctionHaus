package auctionhaus

class MaxBidForListingTagLib {

    def maxBidForListingTagLib = { attrs ->
        if (attrs.listingInstance){

            out << """<span class="listingInstance ${attrs.listingInstance.bids.bidAmount.max()}">${attrs.listingInstance.bids.bidAmount.max().encodeAsHTML()}</span>"""
        }



        }

    }


