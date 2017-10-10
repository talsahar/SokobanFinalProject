package controller.command.records;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import model.records.RecordList;
import others.CommandNames;
import server.MyClient;
import view.View;

public class SortRecordsCommand extends SokobanCommand {

	public SortRecordsCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.SortRecords);
	}

	@Override
	public void execute() {
		String by = params.remove(0);
		RecordList records = model.getCurrentRecordList();
		switch (by) {
		case "time":
			records.sort(2);
			break;
		case "steps":
			records.sort(1);
			break;
		case "levelName":
			records.sort(3);
			break;
		}
		records.setCurrentPage(0);

		view.getFrontDisplayer().showRecords(records.getCurrentPageList());
	}

}
