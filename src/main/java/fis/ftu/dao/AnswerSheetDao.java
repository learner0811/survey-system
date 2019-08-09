package fis.ftu.dao;

import java.util.List;

import org.hibernate.Query;
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
	
	public List<AnswerSheet> findAll(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select a from answer_sheet a join fetch a.survey");
		List<AnswerSheet> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public List<AnswerSheet> findBySurveyId(int idsurvey){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select a from answer_sheet a join fetch a.survey where a.survey.idsurvey = :idsurvey");
		query.setParameter("idsurvey", idsurvey);
		List<AnswerSheet> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public AnswerSheet findById(int id){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select a from answer_sheet a join fetch a.survey where a.idanswerSheet = :idanswerSheet");
		query.setParameter("idanswerSheet", id);
		AnswerSheet answerSheet = (AnswerSheet) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return answerSheet;
	}
}
