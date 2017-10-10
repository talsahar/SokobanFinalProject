package connection;

import java.util.List;

public interface DBIntegrator {
	public void insertToDataBase(Object o);

	public List queryForDatabase(String query);
}
