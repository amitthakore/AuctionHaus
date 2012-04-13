package auctionhaus

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import grails.converters.JSON


class BidController {
  def createBidService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [bidInstanceList: Bid.list(params), bidInstanceTotal: Bid.count()]
    }
    @Secured(['ROLE_USER'])
    def create() {
        [bidInstance: new Bid(params)]
    }
    @Secured(['ROLE_USER'])
    def save() {

        def bidInstance = new Bid(params)

        try{
           bidInstance = createBidService.createNewBid(bidInstance)

             def biddata = [
              bidDateTime : bidInstance.bidDateTime,
              bidAmount : bidInstance.bidAmount,
              bidder :   bidInstance.bidder.username
                          ]

           //render biddata as JSON
            render new JSON(biddata).toString()
            flash.message = message(code: 'bid.created.message')
        }

        catch(grails.validation.ValidationException e) {
      // L-8: Validation errors will be displayed on the listing detail page if an added bid does not pass validation (unit test of the controller action that handles this requirement)
         render flash.message = message(code: 'bid.amount.not.valid')

       }

      }

    def show() {
        def bidInstance = Bid.get(params.id)
        if (!bidInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'bid.label', default: 'Bid'), params.id])
            redirect(action: "list")
            return
        }

        [bidInstance: bidInstance]
    }
    @Secured(['ROLE_USER'])
    def edit() {
        def bidInstance = Bid.get(params.id)
        if (!bidInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bid.label', default: 'Bid'), params.id])
            redirect(action: "list")
            return
        }

        [bidInstance: bidInstance]
    }
    @Secured(['ROLE_USER'])
    def update() {
        def bidInstance = Bid.get(params.id)
        if (!bidInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bid.label', default: 'Bid'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (bidInstance.version > version) {
                bidInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bid.label', default: 'Bid')] as Object[],
                          "Another user has updated this Bid while you were editing")
                render(view: "edit", model: [bidInstance: bidInstance])
                return
            }
        }

        bidInstance.properties = params

        if (!bidInstance.save(flush: true)) {
            render(view: "edit", model: [bidInstance: bidInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'bid.label', default: 'Bid'), bidInstance.id])
        redirect(action: "show", id: bidInstance.id)
    }
  @Secured(['ROLE_USER'])
    def delete() {
        def bidInstance = Bid.get(params.id)
        if (!bidInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'bid.label', default: 'Bid'), params.id])
            redirect(action: "list")
            return
        }

        try {
            bidInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'bid.label', default: 'Bid'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'bid.label', default: 'Bid'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
