package auctionhaus



import org.junit.*
import grails.test.mixin.*

@TestFor(BidController)
@Mock([Bid,Customer,Listing])
class BidControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
     // params["email"] = 'someValidName'
    }

   /* void testIndex() {
        controller.index()
        assert "/bid/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bidInstanceList.size() == 0
        assert model.bidInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.bidInstance != null
    }    */

    void testSave() {
       /* controller.save()

        assert model.bidInstance != null
        assert view == '/bid/create'
            */
       // response.reset()

        //populateValidParams(params)

        def testEndDateTime = new Date() + 1

        def customer = new Customer(email:"unique1@yahoo.com",password: "1234567",createdDate: new Date())

        customer.save()

        def testList = new Listing(listingName: "Apple TV",listingEndDateTime: testEndDateTime, startingBidPrice: 10.00, seller:customer,listingCreatedDate:new Date())
        testList.save()

        def bidTest = new Bid(bidAmount: 20, bidDateTime: new Date(), bidder: customer, listing:testList)

        bidTest.save()
        params.id = bidTest.id;
        controller.save()
        assert Bid.count() == 1
        assert controller.flash.message != null


    }



 /*   void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bid/list'


        populateValidParams(params)
        def bid = new Bid(params)

        assert bid.save() != null

        params.id = bid.id

        def model = controller.show()

        assert model.bidInstance == bid
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bid/list'


        populateValidParams(params)
        def bid = new Bid(params)

        assert bid.save() != null

        params.id = bid.id

        def model = controller.edit()

        assert model.bidInstance == bid
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bid/list'

        response.reset()


        populateValidParams(params)
        def bid = new Bid(params)

        assert bid.save() != null

        // test invalid parameters in update
        params.id = bid.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bid/edit"
        assert model.bidInstance != null

        bid.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bid/show/$bid.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bid.clearErrors()

        populateValidParams(params)
        params.id = bid.id
        params.version = -1
        controller.update()

        assert view == "/bid/edit"
        assert model.bidInstance != null
        assert model.bidInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bid/list'

        response.reset()

        populateValidParams(params)
        def bid = new Bid(params)

        assert bid.save() != null
        assert Bid.count() == 1

        params.id = bid.id

        controller.delete()

        assert Bid.count() == 0
        assert Bid.get(bid.id) == null
        assert response.redirectedUrl == '/bid/list'
    } */
}
