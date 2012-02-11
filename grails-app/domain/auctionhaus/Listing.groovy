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
  //Listing belongs to a customer and has many bids
    static belongsTo = [seller: Customer]
    static hasMany = [bids: Bid]

  //Get highest Bid Amount for the listing
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

    // Get the next minimum bid a bidder can place
    BigDecimal getMinBid()

    {
        if (bids)        {

     getHighestBid() + bidIncAmt
        }
        else{

        StartingBidPrice
    }

    }

    // Get the winner(Customer) for the listing

        Customer getWinner() {

         listingEndDateTime>= new Date() ? getWinningBid().bidder : null

    }

    Bid getWinningBid()
    {

     Bid.findByListingAndBidAmount(this,getHighestBid())
    }

    static constraints = {

        //L1:Listing name must be less than 64 characters
        listingName nullable: false, blank:false,size:1..63

        //L2 : LisitngBidPrice can not be blank
        startingBidPrice blank:false
        //L3 : Listing description should be less than 255 characters
        listingDescription size:0..255

        //L3: Listing Date time must be in future
        listingEndDateTime nullable:false,validator: {
         if (it.compareTo(new Date())>0)
        return ('invalid ListingEndDateTime')
        }
    }

}