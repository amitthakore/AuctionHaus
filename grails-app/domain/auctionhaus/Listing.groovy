package auctionhaus

import org.codehaus.groovy.grails.validation.BlankConstraint
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion

class Listing {
    String listingName
    Date  listingEndDateTime
    BigDecimal startingBidPrice
    String listingDescription
    BigDecimal bidIncAmt = 0.5
    Customer seller

    static belongsTo = [seller: Customer]
    static hasMany = [bids: Bid]


    BigDecimal getHighestBid() {

        def highestBid

     if (bids) {

         highestBid = Bid.createCriteria().get() {
              eq('listing',this)
              projections
                      {
                          max "bidAmount"
                      }
         }

        }

    }

    BigDecimal GetMinBid()

    {
        if (bids)        {

     getHighestBid() + bidIncAmt
        }
        else{

        StartingBidPrice
    }

    }


    static constraints = {

        //L1:Listing name must be less than 64 characters
        listingName nullable: false, blank:false,size:1..63

        //L2 : LisitngBidPrice can not be blank
        startingBidPrice blank:false
        //L3 : Listing description should be less than 255 characters
        listingDescription size:0..255

        //L3: Listing Date time must be in future
        listingEndDateTime nullable:false,validator: {val,obj ->
         if (val.compareTo(new Date())>0)
        return ('invalid ListingEndDateTime')
        }
    }

}