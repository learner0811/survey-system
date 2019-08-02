package fis.ftu.configuration;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	@Autowired
	public EntityManagerFactory entityManagerFactory;	
	
	@Bean
	public SessionFactory sessionFactory() {
		if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("Session is not hibernate factory");
		}
		return entityManagerFactory.unwrap(SessionFactory.class);
	}
}
