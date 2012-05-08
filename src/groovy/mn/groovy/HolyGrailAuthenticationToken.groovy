package mn.groovy

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class HolyGrailAuthenticationToken  extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 1

	// We're treating 'favoriteColor' as the password
	// What is your quest
	final String quest

	HolyGrailAuthenticationToken(principal, credentials, String yourQuest) {
		super(principal, credentials)
		quest = yourQuest
	}

	HolyGrailAuthenticationToken(principal, credentials, String yourQuest, Collection authorities) {
		super(principal, credentials, authorities)
		quest = yourQuest
	}
}
