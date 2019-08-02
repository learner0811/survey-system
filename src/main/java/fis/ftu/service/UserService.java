package fis.ftu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fis.ftu.dao.UserDao;
import fis.ftu.model.User;
import fis.ftu.util.AppException;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public User authentication(String username, String password) throws AppException {
		return userDao.authentication(username, password);
	}
	
	@Transactional
	public void register(User user) throws AppException {
		userDao.Register(user);
	}
}
