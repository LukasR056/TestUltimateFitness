package at.fh.swenga.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.model.LogModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserService;
import at.fh.swenga.repository.ForumentryRepository;
import at.fh.swenga.repository.LogRepository;
import at.fh.swenga.repository.UserQueryRepository;
import at.fh.swenga.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserQueryRepository userQueryRepository;

	/*
	 * @Autowired LogRepository logRepository;
	 * 
	 * @Autowired ForumentryRepository forumentryRepository;
	 */
	// test fï¿½r branch

	@RequestMapping(value = { "/" })
	public String index(Model model) {

		// test users erstellen und gespeichert.

		Date now = new Date();

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

		model.addAttribute("users", users);
		return "index";
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

	@RequestMapping(value = { "/profile" })
	public String getProfile(Model model) {

		UserModel user = null;

		String searchString = "MaMu";

		user = userQueryRepository.findByUserName(searchString);

		model.addAttribute("user", user);
		return "profile";
	}

	@RequestMapping(value = { "/userSettings" }, method = RequestMethod.GET)
	public String getUserSettings(Model model, @RequestParam String userName) {

		UserModel user = userQueryRepository.findByUserName(userName);

		if (user != null) {
			model.addAttribute("user", user);
			return "userSettings";
		} /*
			 * else { model.addAttribute("errorMessage", "Couldn't find user " + userName);
			 * return "profile"; }
			 */
		return "profile";

	}

	@RequestMapping(value = { "/userSettings" }, method = RequestMethod.POST)
	public String editUser(@Valid UserModel changedUserModel, BindingResult bindingResult, Model model) {

		String[] partUserName = (changedUserModel.userName).split(",");
		
		UserModel user = userQueryRepository.findByUserName(partUserName[0]); 
		//keine schöne lösung, wieso ist aber userName=MaMu,MaMu,??

		
		 //* if (user == null) { model.addAttribute("errorMessage",
		// * "User does not exist!<br>"); } 	

		 
		user.setFirstName(changedUserModel.getFirstName());
		user.setLastName(changedUserModel.getLastName());
		user.seteMail(changedUserModel.geteMail());		
		user.setHeight(changedUserModel.getHeight());
		user.setWeight(changedUserModel.getWeight());
		
		user = userRepository.merge(user);
		
		//unser Ansatz um das Problem zu löschen. Jedoch funktioniert die zuweisung zu user nicht.
		
		 
		
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