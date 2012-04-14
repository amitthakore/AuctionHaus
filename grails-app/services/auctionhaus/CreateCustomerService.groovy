package auctionhaus
import auctionhaus.CustomerRole

class CreateCustomerService {

    Customer createNewCustomer(Customer customerInstance) {

    if (!customerInstance.hasErrors() && customerInstance.save()) {

           def customerRoleInstance = new CustomerRole()

           customerRoleInstance.customer = customerInstance
           customerRoleInstance.role = Role.findByAuthority("ROLE_USER")
           customerRoleInstance.save(flush: true)

        } else {

            throw new grails.validation.ValidationException("Customer Error",customerInstance.errors)
        }
        return customerInstance
    }
}


