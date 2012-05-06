package mn.groovy

import org.apache.commons.lang.builder.HashCodeBuilder

class KnightRole implements Serializable {

	Knight knight
	Role role

	boolean equals(other) {
		if (!(other instanceof KnightRole)) {
			return false
		}

		other.knight?.id == knight?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (knight) builder.append(knight.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static KnightRole get(long knightId, long roleId) {
		find 'from KnightRole where knight.id=:knightId and role.id=:roleId',
			[knightId: knightId, roleId: roleId]
	}

	static KnightRole create(Knight knight, Role role, boolean flush = false) {
		new KnightRole(knight: knight, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(Knight knight, Role role, boolean flush = false) {
		KnightRole instance = KnightRole.findByKnightAndRole(knight, role)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(Knight knight) {
		executeUpdate 'DELETE FROM KnightRole WHERE knight=:knight', [knight: knight]
	}

	static void removeAll(Role role) {
		executeUpdate 'DELETE FROM KnightRole WHERE role=:role', [role: role]
	}

	static mapping = {
		id composite: ['role', 'knight']
		version false
	}
}
