package auctionhaus

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion

class Bid {
     Integer bidAmount
     Integer previousBidAmount
     Date    bidDatetime
    static belongsTo = [customer:Customer]
    static constraints = {
    bidAmount nullable:false, validator: {val,obj -> if (obj.bidAmount <  (obj.previousBidAmount+= 50/100))
        return ('invalid Bid') }
    bidDatetime blank:false
   
    }
    }
