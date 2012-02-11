package auctionhaus

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion

class Bid {
     BigDecimal bidAmount
     Date    bidDatetime
    static belongsTo = [listing : Listing, bidder :Customer]
    static constraints = {

    bidDatetime blank:false
   
    }
    }
