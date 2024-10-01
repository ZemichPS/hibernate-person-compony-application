package util;

import lombok.Getter;
import models.Company;
import models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.management.relation.Role;
import java.util.Properties;

public class HibernateUtil {

    // Method to get the SessionFactory
    // Singleton instance of SessionFactory
    @Getter
    private static final SessionFactory sessionFactory;

    // Static block to initialize the SessionFactory
    static {
        try {
            // Create configuration object and configure it from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Company.class);



            // Create ServiceRegistry using the configuration settings
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            // Build the SessionFactory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Log the exception or rethrow it (depending on your needs)
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Method to get a new Session from the SessionFactory
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Method to close the SessionFactory (e.g., during application shutdown)
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
