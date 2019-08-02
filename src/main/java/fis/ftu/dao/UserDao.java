package fis.ftu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fis.ftu.model.Account;
import fis.ftu.model.User;
import fis.ftu.util.AppException;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public User authentication(String username, String password) throws AppException{
		Session session = sessionFactory.openSession();
		session.beginTransaction().begin();
		Account account = (Account) session.createCriteria(Account.class)
			.add(Restrictions.eq("username", username))
			.add(Restrictions.eq("password", password)).uniqueResult();
		if (account == null) {
			throw new AppException("Mật khẩu hoặc tài khoản không chính xác");
		}			
		User user = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("account.idaccount", account.getIdaccount())).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return user;		
	}
	
	public void Register(User user) throws AppException{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Account checkAccount = (Account) session.createCriteria(Account.class).add(Restrictions.eq("username", user.getAccount().getUsername())).uniqueResult();
		if (checkAccount != null)
			throw new AppException("Tên tài khoản đã tồn tại trong hệ thống");
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}	
}
