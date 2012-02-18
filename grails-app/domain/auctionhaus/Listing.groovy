package auctionhaus

import org.codehaus.groovy.grails.validation.BlankConstraint
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion

class Listing {
    String listingName
    Date  listingEndDateTime
    BigDecimal startingBidPrice
    String listingDescription
    Customer seller
    BigDecimal bidIncAmt = 0.5
    Collection<Bid> bids
  //Listing belongs to a customer and has many bids
    static belongsTo = [seller: Customer]
    static hasMany = [bids: Bid]

    //Get highest Bid Amount for the listing
    BigDecimal getHighestBid() {


        def highestBid = startingBidPrice

        bids.each{
            if (it.bidAmount > highestBid)
                highestBid = it.bidAmount
        }

        return highestBid


    }


    //Get highest Bid Amount for the listing
    BigDecimal getNextHighestBid(bidAmount) {


        def highestBid = startingBidPrice

        bids.each{
            if ((it.bidAmount > highestBid) && (it.bidAmount < bidAmount))
                highestBid = it.bidAmount
        }

        return highestBid


    }



    //Get  Bid Amount for previous bid
    BigDecimal getBidAmountPreviousBid(Bid b) {

        def currentBid
        def previousBid

        def done = false;

        bids.each{
            previousBid = currentBid
            currentBid = it

            //if (done){
              //      return currentBid.bidAmount
              //}

            if (currentBid.bidAmount == b.bidAmount)
            {
            //  done = true;
                if(previousBid == null)    {
                    return startingBidPrice
                }
                else       {
                    return previousBid.bidAmount
                 }
            }
        }

       return startingBidPrice


    }


    // Get the next minimum bid a bidder can place
    BigDecimal getMinBid()

    {

        if (bids)
        {

            return getHighestBid() + bidIncAmt
        }
        else{

             return startingBidPrice
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

        //L-1:Listing name must be less than 64 characters
        listingName nullable: false, blank:false,size:1..63

        //L-2 : LisitngBidPrice can not be blank
        startingBidPrice blank:false
        //L-3 : Listing description should be less than 255 characters      - optional field
        listingDescription nullable:true , size:0..255

        //L-4: Listing Date time must be in future
        listingEndDateTime nullable:false,validator: {
                if (it.compareTo(new Date())<0)
                    return ('invalid listingEndDateTime')
        }




        //B-4: A Listing has a list of Bids for that Listing (unit test)
        bids nullable: true, validator: {


                    val, obj ->

                    //check each bid and see that its listing property matches instance of listing object
            
                    val.each{
                        if(it.listing != obj)
                        {
                              return false
                        }

                    }
                    return true
                }




        
    }

}