package auctionhaus

import grails.test.mixin.*
import org.junit.*


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
//sRV-1Test
@TestFor(CreateCustomerService)
@Mock([Customer,Role,CustomerRole])
class CreateCustomerServiceTests {

    void testCustomerSaveOK()
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

    void testCustomerSaveError()
    {
        def customerOK
        def customerError
       try{
        mockForConstraintsTests(Customer)

       customerOK = new Customer()

        customerOK.password =  "1234567"
        customerOK.username = "athakore@hotmail.com"
        customerOK.enabled = "true"
        customerOK.createdDate = new Date()
        //Groovy's meta-programming to override the encodePassword method customer object is saved and we don't
        // care about the password for this test.
        Customer.metaClass.encodePassword = { -> }
        def createCustomerService = new CreateCustomerService()

        createCustomerService.createNewCustomer(customerOK)

        customerError = new Customer()

        customerError.password =  "1234567"
        customerError.username = "athakore@hotmail.com"
        customerError.enabled = "true"
        customerError.createdDate = new Date()

        createCustomerService.createNewCustomer(customerError)
       }
       catch(grails.validation.ValidationException e){

        assert !customerError.validate()

    }

    }
}