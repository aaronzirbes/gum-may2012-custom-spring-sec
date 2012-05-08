import mn.groovy.Knight
import mn.groovy.KnightRole
import mn.groovy.Role

class BootStrap {

    def init = { servletContext ->

		def kotrt = new Role(authority:'ROLE_KNIGHT_OF_THE_ROUND_TABLE')
		if (!kotrt.save()) {
			println "Failed to save the Knights of the Round Table"
			println kotrt.errors
		}
		def koa = new Role(authority:'ROLE_KING_OF_ARGH')
		if (!koa.save()) {
			println "Failed to save the King of Argh"
			println koa.errors
		}

		def sirLauncelot = new Knight(username:'Sir Launcelot', 
			favoriteColor:'Blue', 
			quest:'To seek the Holy Grail', enabled: true)
		if (!sirLauncelot.save()) {
			println "Failed to save Sir Launcelot"
			println sirLauncelot.errors
		} else {
			// Launcelot is a Knight
			KnightRole.create(sirLauncelot, kotrt, false)
			// Launcelot is a King
			KnightRole.create(sirLauncelot, koa, false)
		}

		def sirRobin = new Knight(username:'Sir Robin', 
			favoriteColor:'Yellow', 
			quest:'To seek the Holy Grail', enabled: true)
		if (!sirRobin.save()) {
			println "Failed to save Sir Robin"
			println sirRobin.errors
		} else {
			KnightRole.create(sirRobin, kotrt, false)
		}


    }
    def destroy = {
    }
}
