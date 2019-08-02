package fis.ftu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fis.ftu.model.Category;

@Repository
public class CategoryDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Category> findAll(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Category");
		@SuppressWarnings("unchecked")
		List<Category> categories = query.list();
		return categories;
	}
}
