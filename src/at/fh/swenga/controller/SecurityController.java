package at.fh.swenga.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.model.RoleModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.repository.RoleRepository;
import at.fh.swenga.repository.UserQueryRepository;
import at.fh.swenga.repository.UserRepository;

@Controller
public class SecurityController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserQueryRepository userQueryRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@RequestMapping("/fillUsers")
	@Transactional
	public String fillData(Model model) {
		
		RoleModel adminRole = roleRepository.getRole("ROLE_ADMIN");
		if (adminRole == null) {
			adminRole = new RoleModel("ROLE_ADMIN");
		}
		
		RoleModel coachRole = roleRepository.getRole("ROLE_COACH");
		if (coachRole == null) {
			coachRole = new RoleModel("ROLE_COACH");
		}
		
		RoleModel userRole = roleRepository.getRole("ROLE_USER");
		if (userRole == null) {
			userRole = new RoleModel("ROLE_USER");
		}
		
		
		Date now = new Date();
		
		List<UserModel> users = userRepository.getUsers();

		if (users.isEmpty()) 
		{
			UserModel u1 = new UserModel("Max", "Schwinger", "MaxSng", now, "w", 1.70, 70.5, null, "max@schwinger", 100,
					false, true, "pwd1");
			u1.encryptPassword();
			u1.addRoleModel(userRole);
			u1.addRoleModel(coachRole);
			userRepository.persist(u1);
			
			UserModel u2 = new UserModel("Max", "Musterman", "MaMu", now, "m", 1.80, 80.7, "MaxSng", "max@schwinge2r", 100,
					false, true, "pwd2");
			u2.encryptPassword();
			u2.addRoleModel(userRole);
			userRepository.persist(u2);
			
			UserModel u3 = new UserModel("Ludi", "Poserfrau", "Ludi", now, "w", 1.64, 90.9, null, "max@schwinger3", 100,
					false, true, "pwd3");
			u3.encryptPassword();
			u3.addRoleModel(userRole);
			userRepository.persist(u3);
			
			UserModel u4 = new UserModel("person", "test", "user", now, "w", 1.70, 70.5, "MaxSng", "test@schwinger", 100,
					false, true, "password");
			u4.encryptPassword();
			u4.addRoleModel(userRole);
			userRepository.persist(u4);
			
			UserModel u5 = new UserModel("person", "test", "admin", now, "m", 1.80, 80.7, "MaxSng", "test@schwinge2r", 100,
					false, true, "password");
			u5.encryptPassword();
			u5.addRoleModel(userRole);
			u5.addRoleModel(coachRole);
			u5.addRoleModel(adminRole);
			userRepository.persist(u5);
		}
		
		return "forward:login";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
 
		return "error";
 
	}
}
