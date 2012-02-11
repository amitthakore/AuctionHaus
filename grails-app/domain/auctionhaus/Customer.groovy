package auctionhaus

class Customer {
         String email
         String password
         Date   createddate

    static constraints = {

     email email:true,blank:false,unique: true
     password size: 6..8,blank:false

    }
}
