package mn.groovy

class Knight {

	transient springSecurityService

	// What is your name?
	String username
	// What is your quest?
	String quest
	// What is your favorite color?
	String favoriteColor

	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
	}

	Set<Role> getAuthorities() {
		KnightRole.findAllByKnight(this).collect { it.role } as Set
	}
}
