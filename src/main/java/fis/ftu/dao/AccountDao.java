package fis.ftu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fis.ftu.model.Account;

@Repository
public class AccountDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Account findByUserName(String username) {
		Account account = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		account = (Account) session.createCriteria(Account.class).add(Restrictions.eq("username", username))
				.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return account;
	}
}
