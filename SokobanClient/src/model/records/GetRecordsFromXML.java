package model.records;

public class GetRecordsFromXML {

	public RecordList getRecords(String str) {
		RecordList records = new RecordList();
		String[] tmpList = str.split("<records>")[1].replaceAll("<Record>", "").replaceAll("</records>", "")
				.split("</Record>");
		for (String s : tmpList) {
			s = s.replaceAll("<date>", "").replaceAll("</date>", ",").replaceAll("<lvlName>", "")
					.replaceAll("</lvlName>", ",").replaceAll("<name>", "").replaceAll("</name>", ",")
					.replaceAll("<steps>", "").replaceAll("</steps>", ",").replaceAll("<time>", "")
					.replaceAll("</time>", "");

			String[] array = s.split(",");
			if (array.length == 5)
				records.addRecord(new Record(array[0], array[1], array[2], Integer.parseInt(array[3]),
						Integer.parseInt(array[4])));
		}
		return records;
	}
}
