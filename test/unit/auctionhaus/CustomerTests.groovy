package auctionhaus

import grails.test.mixin.*
import org.junit.*
import java.awt.print.Book
import com.sun.tools.internal.xjc.generator.util.ExistingBlockReference


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Customer)

class CustomerTests
{
    void testCustomerRequiredFields()  {
        mockForConstraintsTests(Customer)
     // C-1 Customer have email address, password and created date fields
     // C-1 Test Email,password and created date are required fields

       def customernull = new Customer()

    assert  !customernull.validate()

    assert "nullable" == customernull.errors["email"]
    assert "nullable" == customernull.errors["password"]
    assert "nullable" == customernull.errors["createddate"]

        //unique constrains
       // customer = new Customer(email:"amitthakore16@gmail.com",password: "123456",createddate: new Date()).save()
       // assert !customer.validate()
       // assert "unique" == customer.errors["email"]
        // unique constrains
    //C-1: Verify that customer object has email,password and create date fields
        def customer = new Customer(email:"amitthakore16@gmail.com",password: "123456",createddate: new Date()) .save()
    }

    void testCustomerValidEmail()  {
        mockForConstraintsTests(Customer)
        def customer = new Customer(email:"amitthakore16@gmail.com",password: "123456",createddate: new Date()) .save()
        assert customer.validate()
        assert customer.email == 'amitthakore16@gmail.com'
    //C-3: Verify that email is a valid email address

    def customeremail = new Customer(email:"amitthakore17@gmail.com",password: "1123456",createddate: new Date()).save()
    assert customeremail.validate()
    assert "email" != customeremail.errors["email"]

    }

    void testCustomerValidPassword()  {
        mockForConstraintsTests(Customer)

            def customerPassword = new Customer(email:"amitthakore17@gmail.com",password: "1123456",createddate: new Date()).save()
            assert customerPassword.validate()
    //C-4: Password must be between 6-8 characters (unit test)
    assert "size" != customerPassword.errors["password"]


    }
    }

