package auctionhaus



import grails.test.mixin.*
import org.junit.*
import java.awt.print.Book
import com.sun.tools.internal.xjc.generator.util.ExistingBlockReference

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Customer)
class CustomerTests {

    void testConstraints()  {

    def existingCustomer = new Customer(email:"amitthakore16@gmail.com",password: "123456",createddate: new Date())

    mockForConstraintsTests(Customer, [existingCustomer])

    def customer = new Customer()
    assert  customer.validate()
    assert "nullable" == customer.errors["email"]
    assert "nullable" == customer.errors["password"]

   // unique constrains
    customer = new Customer(email:"amitthakore16@gmail.com",password: "123456",createddate: new Date())
    assert !customer.validate()
    assert "unique" == customer.errors["email"]

  // unique constrains

        customer = new Customer(email:"amit_thakore@hotmail.com",password: "123456",createddate: new Date())
        assert !customer.validate()
        assert "unique" == customer.errors["email"]
    }


    }

