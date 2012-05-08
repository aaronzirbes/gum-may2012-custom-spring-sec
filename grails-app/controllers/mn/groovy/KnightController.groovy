package mn.groovy

import org.springframework.dao.DataIntegrityViolationException

class KnightController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [knightInstanceList: Knight.list(params), knightInstanceTotal: Knight.count()]
    }

    def create() {
        [knightInstance: new Knight(params)]
    }

    def save() {
        def knightInstance = new Knight(params)
        if (!knightInstance.save(flush: true)) {
            render(view: "create", model: [knightInstance: knightInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'knight.label', default: 'Knight'), knightInstance.id])
        redirect(action: "show", id: knightInstance.id)
    }

    def show() {
        def knightInstance = Knight.get(params.id)
        if (!knightInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'knight.label', default: 'Knight'), params.id])
            redirect(action: "list")
            return
        }

        [knightInstance: knightInstance]
    }

    def edit() {
        def knightInstance = Knight.get(params.id)
        if (!knightInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'knight.label', default: 'Knight'), params.id])
            redirect(action: "list")
            return
        }

        [knightInstance: knightInstance]
    }

    def update() {
        def knightInstance = Knight.get(params.id)
        if (!knightInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'knight.label', default: 'Knight'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (knightInstance.version > version) {
                knightInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'knight.label', default: 'Knight')] as Object[],
                          "Another user has updated this Knight while you were editing")
                render(view: "edit", model: [knightInstance: knightInstance])
                return
            }
        }

        knightInstance.properties = params

        if (!knightInstance.save(flush: true)) {
            render(view: "edit", model: [knightInstance: knightInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'knight.label', default: 'Knight'), knightInstance.id])
        redirect(action: "show", id: knightInstance.id)
    }

    def delete() {
        def knightInstance = Knight.get(params.id)
        if (!knightInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'knight.label', default: 'Knight'), params.id])
            redirect(action: "list")
            return
        }

        try {
            knightInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'knight.label', default: 'Knight'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'knight.label', default: 'Knight'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
