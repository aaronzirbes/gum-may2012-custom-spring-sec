package mn.groovy

class Knight {

	transient springSecurityService

	String username
	String favoriteColor
	String quest
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
