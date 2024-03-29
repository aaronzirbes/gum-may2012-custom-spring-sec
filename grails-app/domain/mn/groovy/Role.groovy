package mn.groovy

class Role {

	String authority

	String toString() { authority }

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
