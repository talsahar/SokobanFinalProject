/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package run;

import java.io.IOException;
import java.io.PrintWriter;

import solution.Action;
import solution.Solution;

public class SolutionWriter {

	public void writeSolution(String fName, Solution solution) {
		try {
			PrintWriter writer = new PrintWriter(fName, "UTF-8");

			writer.println(System.currentTimeMillis());
			for (Action move : solution.getActions()) {
				writer.println(move.getName());

			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
