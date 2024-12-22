package manyToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateInheritanceExample {

	 public static void main(String[] args) {
	        // Load the configuration and build the SessionFactory
	        Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	        		.applySettings(configuration.getProperties())
	        		.build();
	        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	        // Create a session
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;
  
	        try {
	            transaction = session.beginTransaction();
	            
	            // Create a new Department
	            Department department = new Department();
	            department.setName("IT");

	            // Create a new Employee
	            Employee employee = new Employee();
	            employee.setName("John Doe");
	            employee.setDepartment(department); // Set the Department for Employee
	            
	            // Save the Employee (Department will be saved automatically if using cascade)
	            session.save(employee);
	           
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            sessionFactory.close();
	        }
	    }	            
}