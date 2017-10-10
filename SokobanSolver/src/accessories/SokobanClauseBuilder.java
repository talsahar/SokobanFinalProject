/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package accessories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import action.Predicate;
import planner.MyClause;

public class SokobanClauseBuilder {
	PredCreator creator;

	public SokobanClauseBuilder(PredCreator creator) {
		this.creator = creator;
	}

	public List<String> loadFile(String fName) {
		try {
			return Files.lines(Paths.get(fName)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public MyClause buildKnowledgeBase(List<String> level) {
		MyClause kb = new MyClause();
		PredCreator creator = new PredCreator();
		int boxCount = 0;
		int holeCount = 0;
		for (int i = 0; i < level.size(); i++) {
			for (int j = 0; j < level.get(i).length(); j++) {
				String value = j + "," + i;
				switch (level.get(i).charAt(j)) {
				case '#':
					kb.addLast(creator.createWall(value));
					break;
				case ' ':
					kb.addLast(creator.createClear(value));
					break;
				case 'A':
					kb.addLast(creator.createPlayer(value));
					break;
				case '@':
					boxCount++;
					kb.addLast(creator.createBox("b" + boxCount, value));
					break;
				case 'o':
					holeCount++;
					kb.addLast(creator.createHole("t" + holeCount, value));
					break;
				case 'a':
					holeCount++;
					kb.addLast(creator.createPlayerHole("t" + holeCount, value));
					break;
				case 'X':
					holeCount++;
					kb.addLast(creator.createBoxHole("t" + holeCount, value));
					break;
				}
			}
		}

		return kb;

	}

	public MyClause buildGoal(MyClause knowledgeBase) {
		MyClause goal = new MyClause();
		for (Predicate p : knowledgeBase.getPredicates())
			if (p.getType().equals("holeAt") || p.getType().equals("playerHoleAt"))
				goal.addFirst(creator.createBoxHole("?", p.getValue()));
		return goal;
	}

}
