package fis.ftu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fis.ftu.dto.ResponseDTO;
import fis.ftu.model.Survey;
import fis.ftu.service.SurveyService;

@RestController
public class SurveyController {
	@Autowired
	private SurveyService surveyService;	
	
	@RequestMapping(value = "/api/survey", method = RequestMethod.GET)
	public ResponseEntity<List<Survey>> getAll() {
		List<Survey> listSurvey = surveyService.findAll();			
		return new ResponseEntity<List<Survey>>(listSurvey, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/api/survey", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> saveSurvey(@RequestBody Survey survey) throws Exception{				
		surveyService.save(survey);				
		ResponseDTO response = new ResponseDTO();
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/survey", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> editSurvey(@RequestBody Survey survey){		
		ResponseDTO response = new ResponseDTO();
		try {
			surveyService.edit(survey);
			response.setMessage("Edit success");
			response.setMessageType("success");
		} catch(Exception ex) {
			ex.printStackTrace();
			response.setMessage(ex.getMessage());
			response.setMessageType("error");
		}
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/survey/{id}")
	public ResponseEntity<Survey> findSurveyById(@PathVariable int id) {		
		Survey survey = surveyService.findById(id);					
		return new ResponseEntity<Survey>(survey, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/survey/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {		
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			surveyService.deleteSuvey(id);
			responseDTO.setMessage("Delete successfully");
			responseDTO.setMessageType("success");
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setMessageType("error");
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
}
