package fis.ftu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fis.ftu.model.AnswerSheet;

@Repository
public class AnswerSheetDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(AnswerSheet answerSheet) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(answerSheet);
		session.getTransaction().commit();
		session.close();
	}
}
