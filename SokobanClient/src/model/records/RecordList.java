package model.records;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class RecordList implements Serializable {

	private LinkedList<LinkedList<Record>> list;
	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public RecordList() {
		list = new LinkedList<LinkedList<Record>>();
		list.add(new LinkedList<Record>());
		currentPage = 0;
	}

	public void addList(List<Record> oldList) {
		list = new LinkedList<LinkedList<Record>>();
		/// sort here
		list.add(new LinkedList<Record>());

		for (Record r : oldList) {

			addRecord(r);

		}

	}

	public void addRecord(Record newRecord) {

		if (list.get(list.size() - 1).size() == 9) {
			list.add(new LinkedList<Record>());
			list.get(list.size() - 1).add(newRecord);
		}

		else {
			list.get(list.size() - 1).add(newRecord);

		}

	}

	public void clear() {

		list.clear();
	}

	public LinkedList<LinkedList<Record>> getList() {
		return list;
	}

	public void printList() {
		for (LinkedList<Record> l : list) {
			System.out.println("**************");
			for (Record r : l) {

				System.out.println(r);

			}
		}
	}

	public void setList(LinkedList<LinkedList<Record>> list) {
		this.list = list;
	}

	public void sort(int i) {
		List<Record> newList = new LinkedList<Record>();
		for (LinkedList<Record> l : list) {
			for (Record r : l) {
				newList.add(r);
			}

		}

		Collections.sort(newList, new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				if (i == 1)
					return o1.getSteps() - o2.getSteps();
				else if (i == 2)
					return o1.getTime() - o2.getTime();
				else if (i == 3)
					return o1.getLvlName().compareTo(o2.getLvlName());

				return 0;
			}
		});

		addList(newList);
	}

	public List<Record> getCurrentPageList() {
		return list.get(currentPage);
	}

}
