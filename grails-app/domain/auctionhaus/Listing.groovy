package auctionhaus

import org.codehaus.groovy.grails.validation.BlankConstraint
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion

//Amit Thakore and Ben Williams

class Listing {
    String listingName
    Date  listingEndDateTime
    BigDecimal startingBidPrice
    String listingDescription
    Customer seller,winner
    BigDecimal bidIncAmt = 0.5
    Date listingCreatedDate
    Collection<Bid> bids

    //Listing belongs to a customer and has many bids
    static belongsTo = [seller: Customer]
    static hasMany = [bids: Bid]
    //  M-1: The main landing page shows listings sorted by the date they were created (most recent first)
    static mapping = {sort listingCreatedDate: "desc"}


    //Get next highest Bid Amount for the listing
    BigDecimal getNextHighestBid(bidAmount,bidDate) {


        def nextHighestBid = startingBidPrice

        //look at each bid and remember bidAmount of the bid that comes before the argument bidDate
        bids.each{
          //  if ((it.bidAmount > nextHighestBid) &&  ( (it.bidDateTime.before( bidDate ) || (it.bidDateTime.compareTo( bidDate )==0 ) ) ) )
                if ((it.bidAmount > nextHighestBid) &&  ( (it.bidDateTime.compareTo( bidDate ) <0 || (it.bidDateTime.compareTo( bidDate )==0 ) ) ) )
                nextHighestBid = it.bidAmount
        }
        println(nextHighestBid)
        return nextHighestBid


    }

    static constraints = {

        //Listing name must be less than 64 characters
        listingName nullable: false, blank:false,size:1..63

        //LisitngBidPrice can not be blank
        startingBidPrice blank:false
        // Listing description should be less than 255 characters      - optional field
        listingDescription nullable:true , size:0..255

        //Listing Date time must be in future
        listingEndDateTime nullable:false,validator: {
            if (it.compareTo(new Date())<0)
                return ('invalid listingEndDateTime')
        }
        //  Listing has a nullable field for the winner (Customer)
        winner nullable:true


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

    static namedQueries = {

            recentBids { id ->
                eq 'id',id

            }

        }



}