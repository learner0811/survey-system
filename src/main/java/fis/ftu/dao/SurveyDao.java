package fis.ftu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fis.ftu.model.Survey;


@Repository
public class SurveyDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Survey survey) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();			
		session.save(survey);
		session.getTransaction().commit();
		session.close();		
	}
	
	public void edit(Survey survey) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();		
		session.saveOrUpdate(survey);			
		session.getTransaction().commit();
		session.close();		
	}
	
	public Survey findById(int id) {		
		Session session = sessionFactory.openSession();
		session.beginTransaction();			
		//Survey survey = session.get(Survey.class, id);	
		Query query = session.createQuery("select s from survey s where s.idsurvey = :idsurvey").setParameter("idsurvey", id);		
		Survey survey = (Survey) query.uniqueResult();
		session.getTransaction().commit();			
		session.close();
		return survey;
	}
		
	public List<Survey> findAll() {		
		Session session = sessionFactory.openSession();
		session.beginTransaction();			
		Query query = session.createQuery("from survey");		
		List<Survey> listSurvey = query.list();
		session.getTransaction().commit();					
		session.close();		
		return listSurvey;		
	}

	public void deleteSurvey(int id) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete survey where idsurvey = :idsurvey").setParameter("idsurvey", id);
		int rowAffect = query.executeUpdate();
		System.out.println("Delete survey numbers of row affect " + rowAffect);
		session.getTransaction().commit();
		session.close();
	}
}
