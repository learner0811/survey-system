package fis.ftu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
