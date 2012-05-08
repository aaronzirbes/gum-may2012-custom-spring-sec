package mn.groovy



import org.junit.*
import grails.test.mixin.*

@TestFor(KnightController)
@Mock(Knight)
class KnightControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/knight/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.knightInstanceList.size() == 0
        assert model.knightInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.knightInstance != null
    }

    void testSave() {
        controller.save()

        assert model.knightInstance != null
        assert view == '/knight/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/knight/show/1'
        assert controller.flash.message != null
        assert Knight.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/knight/list'


        populateValidParams(params)
        def knight = new Knight(params)

        assert knight.save() != null

        params.id = knight.id

        def model = controller.show()

        assert model.knightInstance == knight
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/knight/list'


        populateValidParams(params)
        def knight = new Knight(params)

        assert knight.save() != null

        params.id = knight.id

        def model = controller.edit()

        assert model.knightInstance == knight
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/knight/list'

        response.reset()


        populateValidParams(params)
        def knight = new Knight(params)

        assert knight.save() != null

        // test invalid parameters in update
        params.id = knight.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/knight/edit"
        assert model.knightInstance != null

        knight.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/knight/show/$knight.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        knight.clearErrors()

        populateValidParams(params)
        params.id = knight.id
        params.version = -1
        controller.update()

        assert view == "/knight/edit"
        assert model.knightInstance != null
        assert model.knightInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/knight/list'

        response.reset()

        populateValidParams(params)
        def knight = new Knight(params)

        assert knight.save() != null
        assert Knight.count() == 1

        params.id = knight.id

        controller.delete()

        assert Knight.count() == 0
        assert Knight.get(knight.id) == null
        assert response.redirectedUrl == '/knight/list'
    }
}
