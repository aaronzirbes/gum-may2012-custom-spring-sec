package mn.groovy

import org.codehaus.groovy.grails.plugins.springsecurity.GormUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.dao.SaltSource
import org.springframework.security.authentication.encoding.PasswordEncoder
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetailsChecker
import org.springframework.security.core.userdetails.UsernameNotFoundException

class BridgeOfDeathAuthenticationProvider implements AuthenticationProvider {

	protected final Logger log = LoggerFactory.getLogger(getClass())

	// These aren't used really
	UserDetailsChecker preAuthenticationChecks
	UserDetailsChecker postAuthenticationChecks

	// This is the authenticate method that gets called by the Authentication Manager
	Authentication authenticate(Authentication auth) throws AuthenticationException {

		// cast the Authentication to a HolyGrailAuthenticationToken
		HolyGrailAuthenticationToken authentication = auth

		// Pull in the authentication triplet
		String username = authentication.name
		String favoriteColor = authentication.credentials
		String quest = authentication.quest
		def authorities

		// This is where the generic userDetails will go
		GrailsUser userDetails

		// Lookup the Knight by name
		Knight.withTransaction{
			def knight = Knight.findByUsername(username)
			if (!knight) {
				log.warn "Knight not found: $username"
				throw new UsernameNotFoundException('User not found', username)
			} else {
				// run injected Pre-Auth checks
				//preAuthenticationChecks.check userDetails
				authorities = knight.authorities.collect{ new GrantedAuthorityImpl(it.toString()) }

				userDetails = new GrailsUser(knight.username, knight.favoriteColor,
					knight.enabled, !knight.accountExpired, !knight.passwordExpired,
					!knight.accountLocked, authorities, knight.id)

				if (authentication.quest == null) {
					log.debug 'Authentication failed: no quest provided'
					throw new BadCredentialsException('Bad quest', userDetails)
				}
				if (authentication.credentials == null) {
					log.debug 'Authentication failed: no credentials provided'
					throw new BadCredentialsException('Bad credentials', userDetails)
				}
				if (knight.quest.toLowerCase() != quest.toLowerCase()) {
					throw new BadCredentialsException('Your quest is not noble enought', userDetails)
				}
				if (knight.favoriteColor.toLowerCase() != favoriteColor.toLowerCase()) {
					throw new BadCredentialsException('Your quest is not noble enought', userDetails)
				}

				// run injected Post-Auth checks
				//postAuthenticationChecks.check userDetails
			}
		}

		// If we got this far, the user logged in successfully, so return a new
		// fully authenticated token
		def authenticatedToken = new HolyGrailAuthenticationToken(userDetails,
			authentication.credentials, quest, authorities)

		// Set the details
		authenticatedToken.details = authentication.details

		// Return the token
		return authenticatedToken
	}

	// Tell the Authentication Manager that we can process HolyGrailAuthenticationTokens
	boolean supports(Class<? extends Object> authenticationClass) {
		HolyGrailAuthenticationToken.isAssignableFrom authenticationClass
	}
}
