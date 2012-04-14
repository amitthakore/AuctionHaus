package auctionhaus

import grails.test.mixin.*
import org.junit.*


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CreateCustomerService)
@Mock([Customer,Role,CustomerRole])
class CreateCustomerServiceTests {

    void testCustomerSave()
    {

        mockForConstraintsTests(Customer)

        Customer customerOK = new Customer()

        customerOK.password =  "1234567"
        customerOK.username = "athakore@hotmail.com"
        customerOK.enabled = "true"
        customerOK.createdDate = new Date()
        //Groovy's meta-programming to override the encodePassword method customer object is saved and we don't
        // care about the password for this test.
        Customer.metaClass.encodePassword = { -> }
        def createCustomerService = new CreateCustomerService()

        createCustomerService.createNewCustomer(customerOK)

        assert customerOK.validate()

    }


}