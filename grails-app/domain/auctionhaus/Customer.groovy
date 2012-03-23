package auctionhaus


//Amit Thakore and Ben Williams

class Customer {
    String email
    String password
    Date   createdDate
    static hasMany= [bids:Bid]


    static constraints = {

        email email:true,blank:false,unique: true
        password size: 6..8,blank:false
        createdDate nullable: false,blank:false
      bids nullable: true

    }


}
