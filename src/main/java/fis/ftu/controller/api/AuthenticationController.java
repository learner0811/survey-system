package fis.ftu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fis.ftu.dto.AccountDTO;
import fis.ftu.dto.PermissionDTO;
import fis.ftu.dto.ResponseDTO;
import fis.ftu.model.User;
import fis.ftu.service.JwtService;
import fis.ftu.service.UserService;
import fis.ftu.util.AppException;
import fis.ftu.util.JwtTokenUtil;

@RestController
public class AuthenticationController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("#{'${list.permission.code}'.split(',')}")
	private List<String> listCodePermission;

	@Value("#{'${list.permission.title}'.split(',')}")
	private List<String> listTitlePermission;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "/api/permissions")
	public ResponseEntity<ResponseDTO> getAllPermission() {
		ResponseDTO responseDTO = new ResponseDTO();
		int size = (listCodePermission.size() > listTitlePermission.size()) ? listTitlePermission.size()
				: listCodePermission.size();
		List<PermissionDTO> listPermissionDTO = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			PermissionDTO permissionDTO = new PermissionDTO();
			permissionDTO.setCode(listCodePermission.get(i));
			permissionDTO.setTitle(listTitlePermission.get(i));
			listPermissionDTO.add(permissionDTO);
		}
		responseDTO.setData(listPermissionDTO);
		responseDTO.setMessageType("success");
		ResponseEntity<ResponseDTO> response = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> login(@RequestBody AccountDTO accountDTO) {
		User user = null;
		ResponseEntity<ResponseDTO> response = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(accountDTO.getUsername(), accountDTO.getPassword()));
			responseDTO.setData(user);
			responseDTO.setMessageType("success");
			responseDTO.setMessage("Success");
			
			//generate token
			UserDetails userDetails = jwtService.loadUserByUsername(accountDTO.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			responseDTO.setData(token);
			response = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {			
			responseDTO.setData(new User());
			responseDTO.setMessage(e.getMessage());
			responseDTO.setMessageType("error");
			response = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		}
		return response;
	}

	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> register(@RequestBody User user) {
		ResponseEntity<ResponseDTO> reponse = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			userService.register(user);
			responseDTO.setMessage("Register successfully");
			responseDTO.setMessageType("success");
			reponse = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (AppException e) {
			if (e.getErrorMsg().equals("")) {
				responseDTO.setMessage(e.getMessage());
				responseDTO.setMessageType("error");
				reponse = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
			} else {
				responseDTO.setMessage(e.getErrorMsg());
				responseDTO.setMessageType("error");
				reponse = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
			}
		}
		return reponse;
	}
}
