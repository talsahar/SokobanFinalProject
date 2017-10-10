/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package action;

public abstract class Predicate {

	protected String type, id, value; // type=boxAt, id=int, value= place

	public Predicate(String type, String id, String value) {

		this.type = type;
		this.id = id;
		this.value = value;
	}

	public boolean contradicts(Predicate other) {

		// if (type.equals(other.type) && id.equals(other.id) &&
		// !value.equals(other.value))
		// return true;

		if (!id.equals(other.id) && value.equals(other.value))
			return true;

		if (!type.equals(other.type) && value.equals(other.value))
			return true;

		return false;
	}

	public boolean equals(Predicate p) {
		return (type.equals(p.type) && id.equals(p.id) && value.equals(p.value));
	}

	public String getID() {
		return id;
	}

	public String getType() {

		return type;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return (type + id + value).hashCode();
	}

	public boolean isSatisfied(Predicate p) {
		return type.equals(p.type) && (id.equals(p.id) || p.id.equals("?")) && (value.equals(p.value));
	}

	@Override
	public String toString() {
		return "{" + type + "," + id + ",(" + value + ")}";
	}

}
