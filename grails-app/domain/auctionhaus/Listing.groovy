package auctionhaus

import org.codehaus.groovy.grails.validation.BlankConstraint
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion

class Listing {
    String listingName
    Date  listingEndDateTime
    Integer startingBidPrice
    String listingDescription
    String winner
    static belongsTo = [customer : Customer]
    static hasMany = [bids: Bid]
    static constraints = {
        listingName blank:false,size:1..63
        listingEndDateTime blank:false
        startingBidPrice blank:false
        listingDescription size:0..255
        listingEndDateTime validator:
                {if (it.before(new Date() +1))
        return ('invalid BidEndDateTime')
        }

    }

}