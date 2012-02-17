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
    void testUniqueEmail(){

  //    C-2: Email address must be a unique field (integration test)
   def customer = new Customer(email:"amitthakore16@gmail.com",password: "123456",createdDate: new Date())
        customer.save(flush:true)
   
    customer.email == "amitthakore16@gmail.com"

       customer.save()
        assert 'unique' != customer.errors["email"]
    }
}
