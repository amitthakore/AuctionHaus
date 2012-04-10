
//Amit Thakore
package auctionhaus

import org.springframework.dao.DataIntegrityViolationException
import auctionhaus.Role
import auctionhaus.CustomerRole
import grails.plugins.springsecurity.Secured

class CustomerController {
    def createCustomerService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
   // def adminRole =  'ROLE_ADMIN'

    def index() {
        redirect(action: "list", params: params)
    }
    @Secured(['ROLE_ADMIN'])
    def list() {

        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count()]
    }

    def create() {
        [customerInstance: new Customer(params)]
    }

    def save() {
           def customerInstance
        try{
            customerInstance = createCustomerService.createNewCustomer(params)


        render(view: "show", model: [customerInstance: customerInstance])

		flash.message = message(code: 'customer.created.message', args: [message(code: 'customer.label', default: 'Customer'), customerInstance.username])
        }


    catch (grails.validation.ValidationException e){

        render(view: "create", model: [customerInstance: customerInstance])
    }
    }


    def show() {
        def customerInstance = Customer.get(params.id)
        if (!customerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
            redirect(action: "list")
            return
        }

        [customerInstance: customerInstance]
    }
    @Secured(['ROLE_ADMIN'])
    def edit() {
        def customerInstance = Customer.get(params.id)
        if (!customerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
            redirect(action: "list")
            return
        }

        [customerInstance: customerInstance]
    }
    @Secured(['ROLE_ADMIN'])
    def update() {
        def customerInstance = Customer.get(params.id)
        try{
        if (!customerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (customerInstance.version > version) {
                customerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'customer.label', default: 'Customer')] as Object[],
                          "Another user has updated this Customer while you were editing")
                render(view: "edit", model: [customerInstance: customerInstance])
                return
            }
        }

        customerInstance.properties = params

        if (!customerInstance.save(flush: true)) {
            render(view: "edit", model: [customerInstance: customerInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'customer.label', default: 'Customer'), customerInstance.id])
        redirect(action: "show", id: customerInstance.id)
    }

    catch (grails.validation.ValidationException e){

        render(view: "edit", model: [customerInstance: customerInstance])
    }
    }
    @Secured(['ROLE_ADMIN'])
    def delete() {
        def customerInstance = Customer.get(params.id)
        if (!customerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
            redirect(action: "list")
            return
        }

        //check if there are active listings for this customer
        //criteria query.
        def c = Listing.createCriteria().list {

            eq("seller", customerInstance)
        }

//  C-4: An existing customer can only be deleted through the web interface if they have 0 listings and 0 bids. The system will present an error message to the user if the delete cannot be performed (unit test of the controller action that has this logic)
// customerInstance.bids.size()  throws null pointer exception in unit test if cusotmer has 0 bids..so moving
// bids size to a variable.
        def numOfBids = 0

        try {

             if (customerInstance.bids != null) {

             numOfBids = customerInstance.bids.size()

             }
            if (numOfBids == 0) {
//now check customer listing
                if(c.size() > 0){

                    flash.message = message(code: 'customer.not.deleted.active.listings', args: [message(code: 'customer.label', default: 'Customer'), params.id])
                    redirect(action: "show", id: params.id)
                    return
                }
            customerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
            redirect(action: "list")
            }
            else {
            //Customer has active bids now check if they have listings too
                if(c.size() > 0){

                    flash.message = message(code: 'customer.not.deleted.active.bid.listings', args: [message(code: 'customer.label', default: 'Customer'), params.id])
                    redirect(action: "show", id: params.id)
                    return
                }
                else {
                //Customer has no listing but has bids
                flash.message = message(code: 'customer.not.deleted.active.bids', args: [message(code: 'customer.label', default: 'Customer'), params.id])
                redirect(action: "show", id: params.id)
                }
            }
        }
       catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'customer.not.deleted', args: [message(code: 'customer.label', default: 'Customer'), params.id])
            redirect(action: "show", id: params.id)
        }

    }


}
