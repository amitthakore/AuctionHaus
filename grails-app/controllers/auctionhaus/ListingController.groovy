package auctionhaus

import org.springframework.dao.DataIntegrityViolationException

class ListingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
      //M-2: The main landing page shows up to 5 listings at a time
      // M-3: If more than 5 listings exist, pagination links will be available to let the user page through the listings
        params.max = Math.min(params.max ? params.int('max') : 5, 100)
      //M-4: Only listings with a end date/time that is in the future are visible on the main page
        def listingInstance = Listing.list(params).findAll {it.listingEndDateTime >= new Date()}

        [listingInstanceList:listingInstance, listingInstanceTotal: Listing.count()]
    }

    def create() {
        [listingInstance: new Listing(params)]
    }

    def save() {
        def listingInstance = new Listing(params)

        try{
        if (!listingInstance.save(flush: true)) {
            render(view: "create", model: [listingInstance: listingInstance])
            return
        }

		flash.message = message(code: 'listing.created.message', args: [message(code: 'listing.label', default: 'Listing'), listingInstance.id])
        redirect(action: "show", id: listingInstance.id)
    }
        catch (grails.validation.ValidationException e){
            flash.message = message(code: 'Listing.listingEndDateTime.Invalid')
            render(view: "create", model: [listingInstance: listingInstance])
        }
    }

    def show() {
        def listingInstance = Listing.get(params.id)

        if (!listingInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'listing.label', default: 'Listing'), params.id])
            redirect(action: "list")
            return
        }
        // call sellername method to get user portion of email
        // L-6: The detail page for the listing shows only the user portion of the email address of the user who created the listing (e.g. “mike” if the email address is “mike@piragua.com”)
        def sellername
        sellername = sellerName(listingInstance)
        [listingInstance: listingInstance,sellername:sellername]


    }

    def edit() {
        def listingInstance = Listing.get(params.id)
        if (!listingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listing.label', default: 'Listing'), params.id])
            redirect(action: "list")
            return
        }
        def sellername
        sellername = sellerName(listingInstance)
        [listingInstance: listingInstance,sellername:sellername]
    }

    def update() {
        def listingInstance = Listing.get(params.id)
        if (!listingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listing.label', default: 'Listing'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (listingInstance.version > version) {
                listingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'listing.label', default: 'Listing')] as Object[],
                          "Another user has updated this Listing while you were editing")
                render(view: "edit", model: [listingInstance: listingInstance])
                return
            }
        }

        listingInstance.properties = params

        if (!listingInstance.save(flush: true)) {
            render(view: "edit", model: [listingInstance: listingInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'listing.label', default: 'Listing'), listingInstance.id])
        redirect(action: "show", id: listingInstance.id)
    }

    def delete() {
        def listingInstance = Listing.get(params.id)
        if (!listingInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'listing.label', default: 'Listing'), params.id])
            redirect(action: "list")
            return
        }

        try {
            listingInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'listing.label', default: 'Listing'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'listing.label', default: 'Listing'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

   //L-6: The detail page for the listing shows only the user portion of the email address of the user who created the listing (e.g. “mike” if the email address is “mike@piragua.com”)
    private String sellerName(Listing listingInstance) {

    def sellername = ''
    def sellernames = String[]
    sellernames = listingInstance.seller.email.split("@")
    sellername = sellernames[0]
    return sellername;
}
}
