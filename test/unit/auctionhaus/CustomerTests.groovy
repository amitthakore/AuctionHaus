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
    void testCustomerNullFieldsCauseError()
    {
        mockForConstraintsTests(Customer)

     // C-1 Test Email,password and created date are required fields
     // Create customer object with null values and test if making them nullable gives errors.
        def customerNull = new Customer()

        assert  !customerNull.validate()

        assert "nullable" == customerNull.errors["email"]
        assert "nullable" == customerNull.errors["password"]
        assert "nullable" == customerNull.errors["createdDate"]

    }

    void testCustomerNonNullFieldsOK()
    {
        mockForConstraintsTests(Customer)
    // C-1 Test Email,password and created date are required fields
        def customer = new Customer()
        customer.email = "amitthakore16@gmail.com"
        customer.password = "123456"
        customer.createdDate  = new Date()
        assert customer.validate()

        assert customer.errors["email"] != "nullable"
        assert customer.errors["password"] != "nullable"
        assert customer.errors["createdDate"] != "nullable"
    }


    void testCustomerEmailOK()
    {
        mockForConstraintsTests(Customer)
        def customerEmailOK = new Customer()
        customerEmailOK.email = "amitthakore16@gmail.com"
        customerEmailOK.password = "123456"
        customerEmailOK.createdDate  = new Date()
        assert customerEmailOK.validate()
        assert "email" != customerEmailOK.errors["email"]
    }

    void testCustomerEmailError()
    {
        mockForConstraintsTests(Customer)
    //C-3: Verify that email is a valid email address


        def customerEmail = new Customer()
        customerEmail.email = "amitthakore16gmail.com"
        customerEmail.password = "123456"
        customerEmail.createdDate  = new Date()

        assert !customerEmail.validate()
        assert "email" == customerEmail.errors["email"]

    }

    void testCustomerPasswordError()
    {
        mockForConstraintsTests(Customer)

        def customerPassWord = new Customer()
        customerPassWord.email = "amitthakore16@gmail.com"
        customerPassWord.password = "123459999996"
        customerPassWord.createdDate  = new Date()

        assert !customerPassWord.validate()
    //C-4: Password must be between 6-8 characters (unit test)
        assert "size" == customerPassWord.errors["password"]


    }


    void testCustomerPasswordOK()
    {
        mockForConstraintsTests(Customer)

        def customerPassWord = new Customer()
        customerPassWord.email = "amitthakore16@gmail.com"
        customerPassWord.password = "123459"
        customerPassWord.createdDate  = new Date()

        assert customerPassWord.validate()
        //C-4: Password must be between 6-8 characters (unit test)
        assert "size" != customerPassWord.errors["password"]


    }

}