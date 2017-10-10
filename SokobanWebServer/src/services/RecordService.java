package services;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import connection.DBConnection;
import db.Record;

@Path("records")
public class RecordService {
	@GET
	@Path("/load")
	@Produces(MediaType.APPLICATION_XML)
	public List<Record> getRecords(@DefaultValue("") @QueryParam("player") String player,
			@DefaultValue("") @QueryParam("level") String level) {
		String q = "from Record where (Player='" + player + "' OR '" + player + "'='') AND (Level_Name='" + level
				+ "' OR '" + level + "'='') order by Steps desc";
		return DBConnection.getInstance().queryForDatabase(q);
	}

	@POST 
	public void saveRecord(Record record) {
		DBConnection.getInstance().insertToDataBase(record);
	}

}
