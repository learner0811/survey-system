package fis.ftu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fis.ftu.model.Account;

@Service
public class JwtService implements UserDetailsService {
	@Autowired
	private AccountService accountService;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountService.findAccountByUserName(username);
		if (account != null) {			
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			return new User(account.getUsername(), bcrypt.encode(account.getPassword()),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}