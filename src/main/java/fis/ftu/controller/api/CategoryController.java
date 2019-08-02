package fis.ftu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fis.ftu.model.Category;
import fis.ftu.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/api/categories")
	public ResponseEntity<List<Category>> findAll() {
		List<Category> listCat = categoryService.findAll();
		return new ResponseEntity<List<Category>>(listCat, HttpStatus.OK);

	}	
}
