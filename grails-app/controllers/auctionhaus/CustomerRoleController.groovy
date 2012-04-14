package auctionhaus

import org.springframework.dao.DataIntegrityViolationException

class CustomerRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [customerRoleInstanceList: CustomerRole.list(params), customerRoleInstanceTotal: CustomerRole.count()]
    }

    def create() {
        [customerRoleInstance: new CustomerRole(params)]
    }

    def save() {
        def customerRoleInstance = new CustomerRole(params)
        if (!customerRoleInstance.save(flush: true)) {
            render(view: "create", model: [customerRoleInstance: customerRoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'customerRole.label', default: 'CustomerRole'), customerRoleInstance.id])
        redirect(action: "show", id: customerRoleInstance.id)
    }

    def show() {
        def customerRoleInstance = CustomerRole.get(params.id)
        if (!customerRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerRole.label', default: 'CustomerRole'), params.id])
            redirect(action: "list")
            return
        }

        [customerRoleInstance: customerRoleInstance]
    }

    def edit() {
        def customerRoleInstance = CustomerRole.get(params.id)
        if (!customerRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerRole.label', default: 'CustomerRole'), params.id])
            redirect(action: "list")
            return
        }

        [customerRoleInstance: customerRoleInstance]
    }

    def update() {
        def customerRoleInstance = CustomerRole.get(params.id)
        if (!customerRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerRole.label', default: 'CustomerRole'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (customerRoleInstance.version > version) {
                customerRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'customerRole.label', default: 'CustomerRole')] as Object[],
                        "Another user has updated this CustomerRole while you were editing")
                render(view: "edit", model: [customerRoleInstance: customerRoleInstance])
                return
            }
        }

        customerRoleInstance.properties = params

        if (!customerRoleInstance.save(flush: true)) {
            render(view: "edit", model: [customerRoleInstance: customerRoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'customerRole.label', default: 'CustomerRole'), customerRoleInstance.id])
        redirect(action: "show", id: customerRoleInstance.id)
    }

    def delete() {
        def customerRoleInstance = CustomerRole.get(params.id)
        if (!customerRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerRole.label', default: 'CustomerRole'), params.id])
            redirect(action: "list")
            return
        }

        try {
            customerRoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'customerRole.label', default: 'CustomerRole'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'customerRole.label', default: 'CustomerRole'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
