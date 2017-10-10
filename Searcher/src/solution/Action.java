/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package solution;

public class Action {

	private String name;

	public Action(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object action) {

		return name.equals(((Action) action).name);

	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
