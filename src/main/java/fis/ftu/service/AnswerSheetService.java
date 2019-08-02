package fis.ftu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fis.ftu.dao.AnswerSheetDao;
import fis.ftu.model.AnswerSheet;

@Service
public class AnswerSheetService {
	@Autowired
	private AnswerSheetDao answerSheetDao;
	
	@Transactional
	public void save(AnswerSheet answerSheet) {
		answerSheetDao.save(answerSheet);
	}
}
