package connection;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBConnection implements DBIntegrator {

	private static DBConnection connection;
	private Session session;
	private Transaction tx;

	private DBConnection() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		session = new Configuration().configure().buildSessionFactory().openSession();

	}

	public static DBConnection getInstance() {
		if (connection == null)
			connection = new DBConnection();
		return connection;
	}

	@Override
	public void insertToDataBase(Object o) {
		try {
			tx = session.beginTransaction();
			session.save(o);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List queryForDatabase(String queryString) {

		return session.createQuery(queryString).getResultList();

	}

}
