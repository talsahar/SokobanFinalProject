package controller.command.records;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import model.records.RecordList;
import others.CommandNames;
import server.MyClient;
import view.View;

public class NextPageRecordsCommand extends SokobanCommand {

	public NextPageRecordsCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.NextPageRecords);
	}

	@Override
	public void execute() {
		RecordList records = model.getCurrentRecordList();
		if (records.getList().size() - 1 == records.getCurrentPage())
			records.setCurrentPage(0);
		else
			records.setCurrentPage(records.getCurrentPage() + 1);

		view.getFrontDisplayer().showRecords(records.getCurrentPageList());

	}

}
