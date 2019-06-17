package at.fh.swenga.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import at.fh.swenga.model.LogModel;
import at.fh.swenga.model.RoleModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserService;
import at.fh.swenga.repository.ForumentryRepository;
import at.fh.swenga.repository.LogRepository;
import at.fh.swenga.repository.RoleRepository;
import at.fh.swenga.repository.UserQueryRepository;
import at.fh.swenga.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserQueryRepository userQueryRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@InitBinder
	public void initDateBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new 
				CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/*
	 * @Autowired LogRepository logRepository;
	 * 
	 * @Autowired ForumentryRepository forumentryRepository;
	 */
	// test fuer branch

	@RequestMapping(value = { "/" })
	public String index(Model model) {

		// test users erstellen und gespeichert.

		/*Date now = new Date();

		// Rolen erstellen
		
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
		
		// holen der User aus der Datenbank
		List<UserModel> users = userRepository.getUsers();

		if (users.isEmpty()) {
			UserModel u1 = new UserModel("Max", "Schwinger", "MaxSng", now, "w", 1.70, 70.5, 2, "max@schwinger", 100,
					false, true, "pwd1");
			userRepository.persist(u1);
			UserModel u2 = new UserModel("Max", "Musterman", "MaMu", now, "m", 1.80, 80.7, 2, "max@schwinge2r", 100,
					false, true, "pwd2");
			userRepository.persist(u2);
			UserModel u3 = new UserModel("Max", "Musterfrau", "Peter", now, "w", 1.64, 90.9, 2, "max@schwinger3", 100,
					false, true, "pwd3");
			userRepository.persist(u3);

		}
		
		
		
		UserModel u1 = new UserModel("person", "test", "user", now, "w", 1.70, 70.5, 2, "test@schwinger", 100,
				false, true, "password");
		u1.encryptPassword();
		userQueryRepository.save(u1);
		roleRepository.persist(userRole);
		u1.addRoleModel(userRole);
		userRole.addUser(u1);
		//roleRepository.persist(userRole);
		//userQueryRepository.save(u1);
		
		UserModel u2 = new UserModel("person", "test", "admin", now, "m", 1.80, 80.7, 2, "test@schwinge2r", 100,
				false, true, "password");
		u2.encryptPassword();
		u2.addRoleModel(userRole);
		u2.addRoleModel(coachRole);
		u2.addRoleModel(adminRole);
		userRepository.persist(u2);

		model.addAttribute("users", users);
		
		 /*List<RoleModel> roles = roleRepository.getAllRoles();
         if (roles.isEmpty()) {
                RoleModel r1 = new RoleModel("ADMIN");
                roleRepository.persist(r1);
               
                RoleModel r2 = new RoleModel("COACH");
                roleRepository.persist(r2);
               
                RoleModel r3 = new RoleModel("USER");
                roleRepository.persist(r3);
         } */
		
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

		if (users.isEmpty()) {
			UserModel u1 = new UserModel("person", "test", "user", now, "w", 1.70, 70.5, null, "test@schwinger", 100,
					false, true, "password");
			u1.encryptPassword();
			u1.addRoleModel(userRole);
			userRepository.persist(u1);
			
			UserModel u2 = new UserModel("person", "test", "admin", now, "m", 1.80, 80.7, null, "test@schwinge2r", 100,
					false, true, "password");
			u2.encryptPassword();
			u2.addRoleModel(userRole);
			u2.addRoleModel(coachRole);
			u2.addRoleModel(adminRole);
			userRepository.persist(u2);
		}
		
		return "login";
		
		//return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {//@Valid UserModel changedUserModel, BindingResult bindingResult, Model model) {
		
		//UserModel user = userQueryRepository.findByUserName(changedUserModel.userName);
				
		return "login";
	}
	@RequestMapping(value = { "/exercise" })
	public String getExercise(Model model) {
		return "exercise";
	}

	@RequestMapping(value = { "/picture" })
	public String getPicture(Model model) {
		return "picture";
	}

	@RequestMapping(value = { "/forum" })
	public String getForum(Model model) {
		return "forum";
	}
	
	@RequestMapping(value = { "/registration" })
	public String getRegistration(Model model) {
		List<UserModel> coaches = null;
		coaches = userQueryRepository.findCoach();
		model.addAttribute("coaches", coaches);
		
		return "registration";
	}
	
	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public String addUser(@Valid UserModel newUserModel, BindingResult bindingResult, Model model) {
	
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "registration";
		}
		
		UserModel user = userQueryRepository.findByUserName(newUserModel.getUserName());
				//new UserModel();
		
		if (user != null) {
			model.addAttribute("errorMessage", "User already exists!");
			return "registration";
		} else {
			//System.out.print(newUserModel.getPassword());
			//newUserModel.encryptPassword();
			userRepository.persist(newUserModel);
			System.out.print("ERFOLGREICH!");
			model.addAttribute("message", "New User " + newUserModel.getUserName() + " successfully added!");
			
			return "login";
		}
		
	} 


	@RequestMapping(value = { "/profile" })
	public String getProfile(Model model, Authentication authentication) {

		UserModel user = null;

		String searchString = authentication.getName();

		user = userQueryRepository.findByUserName(searchString);

		model.addAttribute("user", user);
		return "profile";
	}

	@RequestMapping(value = { "/userSettings1" }, method = RequestMethod.GET)
	public String getUserSettings(Model model, @RequestParam String userName) {

		UserModel user = userQueryRepository.findByUserName(userName);

		if (user != null) {
			model.addAttribute("user", user);
			return "userSettings";
		} /*
			 * else { model.addAttribute("errorMessage", "Couldn't find user " + userName);
			 * return "profile"; }
			 */
		return "forward:/profile";

	}

	@RequestMapping(value = { "/userSettings" }, method = RequestMethod.POST)
	public String editUser(@Valid UserModel changedUserModel, BindingResult bindingResult, Model model) {


		
		 //* if (user == null) { model.addAttribute("errorMessage",
		// * "User does not exist!<br>"); } 	

		
		String[] partUserName = (changedUserModel.userName).split(",");
		
		UserModel user = userQueryRepository.findByUserName(partUserName[0]);
		 
		user.setFirstName(changedUserModel.getFirstName());
		user.setLastName(changedUserModel.getLastName());
		user.seteMail(changedUserModel.geteMail());		
		user.setHeight(changedUserModel.getHeight());
		user.setWeight(changedUserModel.getWeight());
		
		user = userRepository.merge(user);
		
		
		//unser Ansatz um das Problem zu l�sen. Jedoch funktioniert die zuweisung zu user nicht.
		
		 
		
		// coach fehlt noch

		// Save a message for the web page

		model.addAttribute("message", "update succes by " + changedUserModel.toString());
		//model.addAttribute("user", user);
		return "forward:/profile";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}
}