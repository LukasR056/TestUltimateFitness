package at.fh.swenga.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import at.fh.swenga.model.RoleModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.repository.RoleQueryRepository;
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
	
	@Autowired
	RoleQueryRepository roleQueryRepository;
	
	@Transactional
	@RequestMapping("/fillUsers")
	public String fillData(Model model) {
		
		RoleModel adminRole = null;
		
		adminRole = roleRepository.getRole("ROLE_ADMIN");
		if (adminRole == null) {
			adminRole = new RoleModel("ROLE_ADMIN");
			roleRepository.persist(adminRole);
		}
		
		RoleModel coachRole = roleRepository.getRole("ROLE_COACH");
		if (coachRole == null) {
			coachRole = new RoleModel("ROLE_COACH");
			roleRepository.persist(coachRole);
		}
		
		RoleModel userRole = roleRepository.getRole("ROLE_USER");
		if (userRole == null) {
			userRole = new RoleModel("ROLE_USER");
			roleRepository.persist(userRole);
		}
		
		
		Date now = new Date();
		
		List<UserModel> users = userRepository.getUsers();

		if (users.isEmpty()) 
		{
			UserModel u1 = new UserModel("Max", "Reitbauer", "MaxSng", now, "m", 1.70, 70.5, null, "max@reitbauer.at", 100,
					 true, "pwd1", null);
			u1.encryptPassword();
			u1.addRoleModel(userRole);
			u1.addRoleModel(coachRole);
			
			System.out.print(u1);
			
			userRepository.persist(u1);
			
			UserModel u2 = new UserModel("Max", "Musterman", "MaMu", now, "m", 1.80, 80.7, "MaxSng", "max@mustermann.com", 100,
					 true, "pwd2", null);
			u2.encryptPassword();
			u2.addRoleModel(userRole);
			userRepository.persist(u2);
			
			UserModel u3 = new UserModel("Ludi", "Poserfrau", "Ludi", now, "w", 1.64, 90.9, null, "ludi@meme.de", 100,
					 true, "pwd3", null);
			u3.encryptPassword();
			u3.addRoleModel(userRole);
			u3.addRoleModel(coachRole);
			userRepository.persist(u3);
			
			UserModel u4 = new UserModel("Walter", "Verge", "user", now, "w", 1.70, 70.5, "MaxSng", "test@walter.de", 100,
					 true, "password", null);
			u4.encryptPassword();
			u4.addRoleModel(userRole);
			userRepository.persist(u4);
			
			UserModel u5 = new UserModel("Christoph", "Hopfer", "admin", now, "m", 1.80, 80.7, "MaxSng", "schwarz@sadmw.at", 100,
					 true, "password", null);
			u5.encryptPassword();
			u5.addRoleModel(userRole);
			u5.addRoleModel(coachRole);
			u5.addRoleModel(adminRole);
			userRepository.persist(u5);
		}
		
		return "forward:login";
	}
	
	@RequestMapping("/newPassword")
	public String newPassword(@Valid UserModel newUserModel, Model model)
    {
        String userName = newUserModel.getUserName();
        String eMail = newUserModel.geteMail();
        if(userQueryRepository.existingUser(userName, eMail) == 1)
            model.addAttribute("user", newUserModel);
        else
        if(userName != null || eMail != null)
            model.addAttribute("errorMessage", "Username and Email not matching!");
        return "newPassword";
    }
	
	@RequestMapping("/changePassword")
    public String changePassword(@Valid UserModel newUserModel, Model model)
    {
        UserModel user = userQueryRepository.findByUserName(newUserModel.getUserName());
        if(newUserModel.getPassword().equals(newUserModel.getPasswordConfirmed()) && 
        		newUserModel.getPassword().length() >= 6)
        {
            user.setPassword(newUserModel.getPassword());
            user.encryptPassword();
            model.addAttribute("message", "Password successfully changed!");
            return "login";
        } else
        {
            model.addAttribute("errorMessage", "Password is not the same or it has to be at least six characters long!");
            model.addAttribute("user", newUserModel);
            return "newPassword";
        }
    }
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
 
		return "error";
 
	}
}
