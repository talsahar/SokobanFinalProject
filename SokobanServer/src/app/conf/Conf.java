/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package app.conf;

/**
 * 
 * 
 * Configuration of fixed variables and commands
 *
 */

public class Conf {

	public static final String verifyPath = "d:/resources/verify.txt";
	public static final String noSuchAnIDExsistNotify = "No Such as ID Exsists";
	public static final int maxConnectionsNumber = 100;
	public static final Integer defaultPort = 5555;

	public static final String serverOnline = "Server Online and Waiting for Connections";
	public static final String serverOffline = "You Closed the Server";

	public static final String newClientNotify = "New Client Arrived :: ID: ";
	public static final String removeClientNotify = "Client Removed :: ID: ";

	public static final String newProcessNotify = "New Process Started: ";
	public static final String stopProcessNotify = "Process End by Admin: ";
	public static final String overProcessNotify = "Process Over: ";

	// response from me
	public static final String recordRespondCommand = "#recordList_ ";
	public static final String solutionCommand = "#solution_ ";
	public static final String noRespond = "No respond";

	// request from clinet
	public static final String solveCommand = "#solve_";
	public static final String recordQuery = "#queryRecord_";
	public static final String saveRecord = "#saveRecord_";

	public static final String removingMessage = "#bye";

}
