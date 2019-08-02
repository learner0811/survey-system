package fis.ftu.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fis.ftu.dao.SurveyDao;
import fis.ftu.model.Survey;
import fis.ftu.repo.SurveyRepo;

@Service
public class SurveyService {
	@Autowired
	private SurveyDao surveyDao;
	
	@Autowired
	private SurveyRepo surveyRepo;
	
	@Transactional
	public void save(Survey survey) throws Exception {
		surveyDao.save(survey);
	}
	
	public void edit(Survey survey) throws Exception{
		//surveyDao.edit(survey); //cant not detect removal orphan ?
		surveyRepo.save(survey);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Survey findById(int id) {
		Survey survey = surveyDao.findById(id);		
		return survey;
	}
	
	@Transactional
	public List<Survey> findAll(){		
		return surveyDao.findAll();
	}
	
	@Transactional
	public void deleteSuvey(int id) throws Exception{		
		surveyDao.deleteSurvey(id);
	}
	
}
