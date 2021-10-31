package TPControlador;

import TPModelo.Agenda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class ConfigHibernate {
   private static SessionFactory sessionFactory;

    public static void load(){

        try {
            AnnotationConfiguration config = new AnnotationConfiguration();
            config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/restdb");
            config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            config.setProperty("hibernate.connection.username", "root");
            config.setProperty("hibernate.connection.password", "mysql");
            config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            config.setProperty("hibernate.show_sql", "true");
            config.setProperty("hibernate.hbm2ddl.auto", "update" );
            config.setProperty("hibernate.c3p0.min_size","0");
            config.setProperty("hibernate.c3p0.max_size","7");
            config.setProperty("hibernate.c3p0.timeout","100");
            config.setProperty("hibernate.c3p0.max_elements","100");
            config.setProperty("hibernate.c3p0.idle_test_period","100");
            config.setProperty("hibernate.c3p0.autoCommitOnClose","true");
            config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");

            config.addAnnotatedClass(Agenda.class);

            sessionFactory = config.buildSessionFactory();
        }
        catch (Exception e) {
           System.out.println("Error: HibernateUtil.HibernateException: " + e.getMessage());
        }
        catch (Throwable ex) {
           ex.printStackTrace();
        }
    }

    public synchronized static Session openSession() {
		return getSessionFactory().openSession();
	}

	public static void closeSession(Session session) {
		session.close();
	}

	public synchronized static SessionFactory getSessionFactory() {
		if(sessionFactory == null)
			load();

		return sessionFactory;
	}

	public synchronized static void closeSessionFactory() {
		try {
			if(sessionFactory != null) {
				sessionFactory.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			sessionFactory = null;
		}
	}

	
	public void destroy() {
		closeSessionFactory();
	}

}
