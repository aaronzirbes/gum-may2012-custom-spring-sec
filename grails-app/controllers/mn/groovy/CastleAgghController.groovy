package mn.groovy

import grails.plugins.springsecurity.Secured

class CastleAgghController {

	def index = { }

	@Secured(['ROLE_KNIGHT_OF_THE_ROUND_TABLE'])
	def gates = {
		render 'You have entered the castle gates and have used the role ROLE_KNIGHT_OF_THE_ROUND_TABLE'
	}   

	@Secured(['ROLE_KING_OF_AGGH'])
	def throne = {
		render 'You have entered the throne room and have used the role ROLE_KING_OF_AGGH'
	}       
}
