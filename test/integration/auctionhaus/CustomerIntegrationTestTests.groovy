package auctionhaus

import static org.junit.Assert.*
import org.junit.*



class CustomerIntegrationTestTests extends GroovyTestCase   {


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
   def customer = new Customer(email:"amitthakore16@gmail.com",password: "123456",createdDate: new Date())
        customer.save(flush:true)
   
    customer.email == "amitthakore16@gmail.com"

       !customer.validate()
        assert 'unique' != customer.errors["email"]
    }

    @Test
    void testUniqueEmailOK(){

        //    C-2: Email address must be a unique field (integration test)
        def customer = new Customer(email:"amitthakore16@gmail.com",password: "123456",createdDate: new Date())
        customer.save(flush:true)

        customer.email == "amitthakore17@gmail.com"

        customer.validate()
        assert 'unique' == customer.errors["email"]
    }

}
