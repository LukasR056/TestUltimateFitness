package at.fh.swenga.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import at.fh.swenga.model.LogModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserService;
import at.fh.swenga.repository.ForumentryRepository;
import at.fh.swenga.repository.LogRepository;
import at.fh.swenga.repository.UserQueryRepository;
//import at.fh.swenga.repository.UserQueryRepository;
import at.fh.swenga.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
 	@Autowired
	UserQueryRepository userQueryRepository;

	
	 /* @Autowired LogRepository logRepository;
	 * 
	 * @Autowired ForumentryRepository forumentryRepository;
	 */
 	// test für branch

	@RequestMapping(value = { "/" })
	public String index(Model model) {

		// test users erstellen und gespeichert.

		Date now = new Date();

		// holen der User aus der Datenbank
		List<UserModel> users = userRepository.getUsers();

		if (users.isEmpty()) {
			UserModel u1 = new UserModel("Max", "Schwinger", "MaxSng", now, "w", 1.70, 70.5, 2, "max@schwinger",
					11.23, 100, false, true);
			userRepository.persist(u1);
			UserModel u2 = new UserModel("Max", "Musterman", "MaMu", now, "m", 1.80, 80.7, 2, "max@schwinge2r", 11.23,
					100, false, true);
			userRepository.persist(u2);
			UserModel u3 = new UserModel("Max", "Musterfrau", "Peter", now, "w", 1.64, 90.9, 2, "max@schwinger3", 11.23,
					 100, false, true);
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

	/*
	 * @RequestMapping("/fillUser") public String fillData(Model model) {
	 * 
	 * DataFactory df = new DataFactory(); String array[]=
	 * {"Niels Bohr","Albert Einstein","Marie Curie","Lord Rayleigh"}; String
	 * array2[]= {"Physics","Chemistry","Literature","Peace"};
	 * 
	 * 
	 * UserModel user = null;
	 * 
	 * for(int i=0;i<15;i++) { if (i%10==0) { String userName=df.getName();
	 * //"User1"; user = userRepository.findFirstByUserName(userName); if
	 * (user==null) { user = new UserModel(userName); } }
	 * 
	 * Calendar dob = Calendar.getInstance(); dob.setTime(df.getBirthDate());
	 * 
	 * UserModel userModel = new UserModel(df.getFirstName(),
	 * array[df.getNumberBetween(0, 4)],array2[df.getNumberBetween(0, 4)],
	 * df.getBirthDate(), "m", 187.5, 76.3, 5, "fdnsdf@gmx.at", 54.3, true, true);
	 * 
	 * 
	 * 
	 * // userModel.setCountry(country); userRepository.save(userModel); }
	 * 
	 * return "forward:list"; }
	 */

	/*
	 * @RequestMapping("/delete") public String deleteData(Model
	 * model, @RequestParam int id) { userRepository.deleteById(id);
	 * 
	 * return "forward:list"; }
	 */

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}