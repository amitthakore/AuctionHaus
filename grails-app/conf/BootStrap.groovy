import auctionhaus.Customer
import auctionhaus.Role
import auctionhaus.CustomerRole

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
        def testUser = new Customer(username: 'me@hotmail.com', enabled: true, password: '1234567',createdDate: new Date())
        testUser.save(flush: true)

        CustomerRole.create testUser, adminRole, true

        assert Customer.count() == 1
        assert Role.count() == 2
        assert CustomerRole.count() == 1



    }
    def destroy = {
    }
}
