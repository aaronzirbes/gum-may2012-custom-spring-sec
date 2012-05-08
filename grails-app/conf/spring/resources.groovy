import mn.groovy.BridgeOfDeathAuthenticationProvider
import mn.groovy.HolyGrailAuthenticationToken
import mn.groovy.KeeperOfTheBridgeFilter

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

// Place your Spring DSL code here
beans = {
	def conf = SpringSecurityUtils.securityConfig

	// keeper of the bridge of death authentication
	authenticationProcessingFilter(KeeperOfTheBridgeFilter) {
		authenticationManager = ref('authenticationManager')
		sessionAuthenticationStrategy = ref('sessionAuthenticationStrategy') 
		authenticationSuccessHandler = ref('authenticationSuccessHandler')
		authenticationFailureHandler = ref('authenticationFailureHandler')
		rememberMeServices = ref('rememberMeServices')
		authenticationDetailsSource = ref('authenticationDetailsSource')
		filterProcessesUrl = conf.apf.filterProcessesUrl
		usernameParameter = conf.apf.usernameParameter
		passwordParameter = conf.apf.passwordParameter
		continueChainBeforeSuccessfulAuthentication = conf.apf.continueChainBeforeSuccessfulAuthentication
		allowSessionCreation = conf.apf.allowSessionCreation
		postOnly = conf.apf.postOnly
	}       

	// keeper of the bridge of death authentication

	// We are replacing the DAO provider rather than registering
	// a new provider
	daoAuthenticationProvider(BridgeOfDeathAuthenticationProvider) {
		preAuthenticationChecks = ref('preAuthenticationChecks')
		postAuthenticationChecks = ref('postAuthenticationChecks')
	}
}
