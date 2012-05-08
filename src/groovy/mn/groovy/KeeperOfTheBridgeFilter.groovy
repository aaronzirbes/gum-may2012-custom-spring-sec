package mn.groovy

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import org.codehaus.groovy.grails.plugins.springsecurity.RequestHolderAuthenticationFilter
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.util.TextEscapeUtils

/** 
 * This class acts a the keeper of the Bridge of Death
 * did in Monty Python and the Holy grail by not
 * letting anyone past until they answer his five...er
 * three questions properly.
 */
class KeeperOfTheBridgeFilter extends RequestHolderAuthenticationFilter {

	@Override
	Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		// Only accept HTTP POST method
		if ( ! request.post) {
			throw new AuthenticationServiceException( "Authentication method not supported: $request.method")
		}

		// read form fields
		String username = (obtainUsername(request) ?: '').trim()
		String quest = request.getParameter('j_quest')
		String favoriteColor = request.getParameter('j_favoriteColor')

		// create authentication token
		def authentication = new HolyGrailAuthenticationToken(username, favoriteColor, quest)

		// save the last entered username so you don't have to
		// keep re-entering it (thanks Burt Beckwith)
		HttpSession session = request.getSession(false)
		if (session || getAllowSessionCreation()) {
			request.session[SPRING_SECURITY_LAST_USERNAME_KEY] =
			TextEscapeUtils.escapeEntities(username)
		}

		// call super.setDetails()
		setDetails request, authentication

		// try to authenticate using the token through the authentication manager
		return getAuthenticationManager().authenticate(authentication)
	}   
}
