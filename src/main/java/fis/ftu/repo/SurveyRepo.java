package fis.ftu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fis.ftu.model.Survey;

@Repository
public interface SurveyRepo extends JpaRepository<Survey, Integer>{
	
}
