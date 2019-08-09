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
import fis.ftu.model.AnswerSheet;
import fis.ftu.service.AnswerSheetService;

@RestController
public class AnswerSheetController {
	@Autowired
	private AnswerSheetService answerSheetService;
	
	@RequestMapping(value = "/api/answersheet", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> save(@RequestBody AnswerSheet answerSheet){
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			answerSheetService.save(answerSheet);
			responseDTO.setMessageType("OK");
			responseDTO.setMessageType("success");
		} catch (Exception ex) {
			ex.printStackTrace();
			responseDTO.setMessageType("error");
			responseDTO.setMessage(ex.getMessage());
		}		
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/answersheets", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> findAll(){
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<AnswerSheet> listAnswer =answerSheetService.findAll();
			responseDTO.setData(listAnswer);
			responseDTO.setMessageType("OK");
			responseDTO.setMessageType("success");
		} catch (Exception ex) {
			ex.printStackTrace();
			responseDTO.setMessageType("error");
			responseDTO.setMessage(ex.getMessage());
		}
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/answersheets/survey/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> findBySurveyId(@PathVariable Integer id){
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<AnswerSheet> listAnswer =answerSheetService.findBySurveyId(id);
			responseDTO.setData(listAnswer);
			responseDTO.setMessageType("OK");
			responseDTO.setMessageType("success");
		} catch (Exception ex) {
			ex.printStackTrace();
			responseDTO.setMessageType("error");
			responseDTO.setMessage(ex.getMessage());
		}
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/test/{id}")
	public AnswerSheet test(@PathVariable Integer id){
		AnswerSheet answerSheet = null;
		answerSheet = answerSheetService.findById(id);
		return answerSheet;
	}
}
