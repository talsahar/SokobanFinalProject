/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package action;

public class StopCondition extends AbstractAction {

	public StopCondition(String type) {
		super(type, "", "");
	}

	public String getStatus() {
		return type;
	}

}
