package auctionhaus

import static org.junit.Assert.*
import org.junit.*

class CustomerIntegrationTests extends GroovyTestCase{
    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testUniqueEmailError(){

        //    C-2: Email address must be a unique field (integration test)
        def customer = new Customer()
        customer.email = "amitthakore16@gmail.com"
        customer.password = "123459"
        customer.createdDate  = new Date()
        customer.save()
        def customer1 = new Customer()
        customer1.email = "amitthakore16@gmail.com"
        customer1.password = "123459"
        customer1.createdDate  = new Date()
        customer1.save()

        assert  !customer1.validate()
        assert customer1.errors["email"] != null
    }

    @Test
    void testUniqueEmailOK(){

        //    C-2: Email address must be a unique field (integration test)
        def customer = new Customer()
        customer.email = "amitthakore16@gmail.com"
        customer.password = "123459"
        customer.createdDate  = new Date()
        customer.save()
        def customer1 = new Customer()
        customer1.email = "amitthakore17@gmail.com"
        customer1.password = "123459"
        customer1.createdDate  = new Date()
        customer1.save()


        assert customer1.validate()
        assert null == customer1.errors["email"]
    }
}
