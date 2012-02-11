package auctionhaus

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion

class Bid {
     BigDecimal bidAmount
     Date    bidDatetime
   //B2 Bids are required to be for a listing (unit Test)
    //B3 Bids are required to have a bidder(unit Test)
    static belongsTo = [listing : Listing, bidder :Customer]


    static constraints = {
    //B1 Bid Date time is a required field
    bidDatetime blank:false
    //B1 Bid are required to have a Bid amount
    //B5 The Bid amount must be at least .50 higher than the previous Bid for the same listing (integration test)
        bidAmount nullable: false, validator: {val, obj, errors ->
            if (obj.listing) {
                def isGood = (val >= obj.listing.minBid)
                if (!isGood) {
                    return ('The Bid amount must be at least .50 higher than the previous Bid for the same listing')
                }
                return isGood
            }
        }

        // B3: Bids are required to have a bidder(integration test)

        bidder(validator: {val, obj, errors ->
            if (obj.listing) {
                println "Errors: ${errors}"
                val != obj.listing.seller
            }
        })

    }


    }

