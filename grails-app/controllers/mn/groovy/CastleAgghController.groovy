package mn.groovy

import grails.plugins.springsecurity.Secured

class CastleAgghController {

	def index = { }

	@Secured(['ROLE_KNIGHT_OF_THE_ROUND_TABLE'])
	def gates = { }   

	@Secured(['ROLE_KING_OF_ARGH'])
	def throne = { }       
}
