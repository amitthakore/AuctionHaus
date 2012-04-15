package auctionhaus

import grails.plugin.jms.JmsService
import grails.converters.JSON
import grails.plugin.mail.MailService
import grails.gorm.*
 //C-2 C-3 Send JMS message and Email
class ListingJMSEmailService {


    def jmsService
    def mailService
    def listingName
    def wseller
    def wbidder
    def wbidAmt
    def maxBidl
    def maxBid
    def fromEmail = "biddeal4u@gmail.com"
    def expiredListing() {
        //get expired listings
        def c = Listing.createCriteria().list {

            lt("listingEndDateTime", new Date())
        }
        //iterate through expired lsits
        c.each() {
            //get winning bid from named query for expired list

            maxBidl  = Bid.topBids(it.id).list(max:1)
            maxBid = maxBidl.get(0)
            listingName = it.listingName
            wseller = it.seller.username
            wbidder = maxBid.bidder.username
            wbidAmt = maxBid.bidAmount

            def listingData = [
                    listingName: listingName,
                    seller:  wseller,
                    bidder: wbidder,
                    bidAmt: wbidAmt
            ]


            if ((it.winner == null) && (it.getBids().size()>0))   {
                def listingDetails = new JSON(listingData).toString()

                //send listingdetails JSON to JMS message Q

               sendJMSMessage(listingDetails)

               // send email
               sendEmail(maxBid,it)

                // update the listing to avoid sending multiple mails/messages
                Listing.executeUpdate("update Listing l set l.winner = ${maxBid.bidder.id} where l.id = ${it.id}")

            }

        }

    }

    //send email to winner/seller
    def sendEmail = {  maxBid,listing->
    mailService.sendMail {

        to maxBid.bidder.username;listing.seller.username
        from "fromEmail"
        subject listing.listingName
        body  "Congratulaitons you are the winner of " + listing.listingName +"." + "Winning bid amount for the listing is " + "\$" +
        maxBid.bidAmount + " " + "http://localhost:8080/AuctionHaus/listing/showWin/" + "${listing.id}"


    }
    }
//Send JMS message
   def sendJMSMessage(String listingDetails){

        jmsService.send(service: 'listening', method: 'receive', "${listingDetails}")
    }
}
