package fis.ftu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.ftu.dao.CategoryDao;
import fis.ftu.model.Category;


@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> findAll(){
		return categoryDao.findAll();
	}
}
