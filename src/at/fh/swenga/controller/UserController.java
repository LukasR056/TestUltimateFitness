package at.fh.swenga.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import at.fh.swenga.model.ExerciseModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserService;
import at.fh.swenga.repository.ExerciseRepository;

//import at.fh.swenga.repository.LogRepository;

import at.fh.swenga.model.ForumentryModel;
import at.fh.swenga.model.RoleModel;

import at.fh.swenga.repository.ForumentryRepository;
import at.fh.swenga.repository.RoleQueryRepository;
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
	ExerciseRepository exerciseRepository;
  @Autowired
	RoleRepository roleRepository;
	@Autowired
	RoleQueryRepository roleQueryRepository;
	@Autowired
	ForumentryRepository forumentryRepository;
	
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
	
	public void findCoaches(Model model) {
		List<UserModel> coaches = null;
		coaches = userQueryRepository.findCoach();
		model.addAttribute("coaches", coaches);
	}


	@RequestMapping(value = { "/" })
	public String index(Model model, String name, String type) {

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

		List<UserModel> users = userQueryRepository.findAll();

		if (users.isEmpty()) {
			UserModel u1 = new UserModel("Max", "Schwinger", "MaxSng", now, "w", 1.70, 70.5, 2, "max@schwinger", 100,
					false, true, "pwd1");
			userRepository.persist(u1);
			UserModel u2 = new UserModel("Max", "Musterman", "MaMu", now, "m", 1.80, 80.7, 2, "max@schwinge2r", 100,
					false, true, "pwd2");
			userRepository.persist(u2);
			UserModel u3 = new UserModel("Max", "Musterfrau", "Peter", now, "w", 1.64, 90.9, 2, "max@schwinger3", 100,
					false, true, "pwd3");

			// hier werden die Daten erstellt

			// Exercises erstellen
			ExerciseModel exercise1 = new ExerciseModel("Hip Lift", "Stomach", null,
					"Du legst dich auf den Rücken und stellst deine Beine hüftbreit auf. Deine Arme liegen ganz locker neben deinem Körper, die Handflächen drücken gegen den Boden. Nun hebst du deine Hüfte so weit an, bis Oberschenkel und Rücken eine gerade Linie bilden. Kurz halten (dabei Po und Bauchmuskeln anspannen) und beim Einatmen die Hüfte wieder absenken. Wichtig: Die Hüfte darf nicht den Boden berühren.");
			ExerciseModel exercise2 = new ExerciseModel("Sit-ups", "Stomach", null,
					" Leg dich auf den Rücken und winkele die Beine an. Halte deine Hände an den Schläfen oder platziere sie hinter deinem Kopf. Die Ellbogen zeigen nach außen, der Blick ist nach oben gerichtet. Nun hebst du mithilfe der Bauchmuskulatur die obere Rückenpartie inklusive der Schulterblätter an bis Oberkörper und Oberschenkel einen 90-Grad-Winkel bilden. Halte diese Position für einige Sekunden und kehre dann wieder in die Grundposition zurück");
			ExerciseModel exercise3 = new ExerciseModel("Up Downs", "Schoulders", null,
					"Starte in der Low Plank Position und drück dich vom Boden weg in die High Plank Position. Achte dabei darauf, dass du den Rumpf anspannst und das Becken so stabil wie möglich hältst. Geh dann wieder zurück in die Low Plank Position und wiederhole die Übung von vorn. Beginne immer abwechselnd mit dem linken bzw. rechten Arm.");
			ExerciseModel exercise4 = new ExerciseModel(" Frontheben mit einer Kurzhantel", "Schoulders", null,
					"Im stabilen Stand wird eine Kurzhantel mit den Handflächen verschränkt und vor der Hüfte gehalten Hebe die Hantel mit leicht gebeugten Armen, ohne mit dem Körper zu pendeln, bis auf Augenhöhe.Lasse anschließend das Gewicht behutsam vor die Hüfte zurück, ohne die Hantel abzulegen.");

			u2.addExercise(exercise1);
			u2.addExercise(exercise4);
			u2.addExercise(exercise3);
			u2.addExercise(exercise2);
			u1.addExercise(exercise1);
			u3.addExercise(exercise2);

			exerciseRepository.save(exercise1);
			exerciseRepository.save(exercise2);
			exerciseRepository.save(exercise3);
			exerciseRepository.save(exercise4);

			userQueryRepository.save(u2);
			userQueryRepository.save(u1);
			userQueryRepository.save(u3);
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
					false, true, "password", null);
			u1.encryptPassword();
			u1.addRoleModel(userRole);
			userRepository.persist(u1);
			
			UserModel u2 = new UserModel("person", "test", "admin", now, "m", 1.80, 80.7, null, "test@schwinge2r", 100,
					false, true, "password", null);
			u2.encryptPassword();
			u2.addRoleModel(userRole);
			u2.addRoleModel(coachRole);
			u2.addRoleModel(adminRole);
			userRepository.persist(u2);
		}
		
		return "login";
		
		

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {
				
		return "login";
	}
	
	@Transactional
	@PostMapping("/logout")
	public String handleLogout(Model model, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		if (authentication != null){    
	        new SecurityContextLogoutHandler().logout(request, response, authentication);
	    }
		
		model.addAttribute("message", "You have been logged out successfully!");
		
	    // return "login";
		return "redirect:/login?logout";		
		
	}
	
	@RequestMapping(value = { "/profile" })
	public String getProfile(Model model, Authentication authentication) {
		
		UserModel user = null;
		
		String searchString = authentication.getName();
		
		user = userQueryRepository.findByUserName(searchString);
		
		model.addAttribute("user", user);
		return "profile";
	}
	
	@RequestMapping(value = { "/exercise" })
	public String getExercise(Model model) {

		// ALLE Exercises von einem User
		UserModel user = null;

		String searchString = "MaMu";

		user = userQueryRepository.findByUserName(searchString);

		model.addAttribute("exercises", user.getExercises());

		return "exercise";
	}

	@RequestMapping(value = { "/showexercise" })
	public String showexercise(Model model, @RequestParam String searchString) {
		//
		// @RequestParam String name
		List<ExerciseModel> exercises = null;
		UserModel user = null;

		String searchStringname = "MaMu";

		user = userQueryRepository.findByUserName(searchStringname);

		// if-else funktioniert nicht!
		switch (searchString) {
		case "AllExercises":
			exercises = exerciseRepository.findAll();
			break;

		default:
			exercises = exerciseRepository.findByType(searchString);
			break;
		}

		System.out.print(searchString);

		// user.addExercise(newExercise);
		// userQueryRepository.updateUser(user);
		// List<ExerciseModel> typeExercise = exerciseRepository.findByType(type);
		model.addAttribute("exercises", user.getExercises());
		model.addAttribute("allexercises", exercises);

		return "exercise";
	}
	//----------------------Exercise dem User hinzufügen
	@RequestMapping(value = {"/addexercise"})
	public String addExercise(Model model, @RequestParam int id)
	{
		UserModel user = null;

		String searchStringname = "MaMu";
		
		user = userQueryRepository.findByUserName(searchStringname);
		
		ExerciseModel newExercise = new ExerciseModel();
				
		newExercise = exerciseRepository.findById(id);
		
		user.addExercise(newExercise);
		
		userRepository.merge(user);
		model.addAttribute("exercises", user.getExercises());
		
		
		return "exercise";
	}
	
	//--------Exercise löschen
	@RequestMapping(value = {"deleteexercise"})
	public String deleteExercise(Model model, @RequestParam int id)
	{
		UserModel user = null;
		String searchStringname = "MaMu";
		user = userQueryRepository.findByUserName(searchStringname);
		
		ExerciseModel newExercise = new ExerciseModel();
		newExercise = exerciseRepository.findById(id);
		
		user.remove(newExercise.getId());
		userRepository.merge(user);
		model.addAttribute("exercises", user.getExercises());
		
		
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
	
	// Param thread wird im HTML abhängig vom tatsächlichen Thread übergeben!	
	@RequestMapping(value = { "/blogEntries" })
	public String getBlogEntries(Model model, @RequestParam String thread, Authentication authentication) {
		UserModel user = null;
		user = userQueryRepository.findByUserName(authentication.getName());
		
		ForumentryModel lastForumentry = forumentryRepository.findTop1ByThreadOrderByCreateDate(thread);
		if (lastForumentry != null) {
			int sizeForumentryModel = forumentryRepository.threadSize(thread);
			UserModel lastForumentryUser = lastForumentry.getUser();
			List<ForumentryModel> forumentries = forumentryRepository.findByThreadOrderByIdDesc(thread);
			
			model.addAttribute("sizeForumentryModel", sizeForumentryModel);
			model.addAttribute("lastForumentryUser", lastForumentryUser);
			model.addAttribute("lastForumentry", lastForumentry);
			model.addAttribute("forumentries", forumentries);
			
			System.out.print(forumentries);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("thread", thread);
		
			
		return "blogEntries";
	}
	
	// Forumentry verspeichern
	@RequestMapping(value = { "/newPost" })
	public String newPost(@Valid ForumentryModel newForumentryModel, BindingResult bindingResult,
			Model model, @RequestParam String thread, Authentication authentication) {
		UserModel user = null;
		user = userQueryRepository.findByUserName(authentication.getName());
		
						
		model.addAttribute("forumentry", newForumentryModel);
		model.addAttribute("user", user);
		model.addAttribute("thread", thread);
		return "newPost";
	}
	
	//@{/safePost(thread=${thread})}
	@Transactional
	@PostMapping("/safePost")
	public String safePost(@Valid ForumentryModel newForumentrymodel, BindingResult bindingResult,
			Model model, @RequestParam String thread, Authentication authentication) {
		
		model.addAttribute("thread", thread);
		newForumentrymodel.setThread(thread);
		newForumentrymodel.setCreateDate(new Date());
		
		UserModel activeUser = userQueryRepository.findByUserName(authentication.getName());
		newForumentrymodel.setUser(activeUser);
				
		//System.out.print(activeUser);
		System.out.print(newForumentrymodel);
		
		if (newForumentrymodel.getText() == "") {
			System.out.print("Text empty");
			model.addAttribute("errorMessage", "Please insert a Text!");
			return "forward:/newPost";
		} else if (newForumentrymodel.getTitle() == "" && newForumentrymodel.getText() != "") {
			newForumentrymodel.setTitle("<no title>");
			
			forumentryRepository.save(newForumentrymodel);
			return "forward:/blogEntries";		
		} else {
			forumentryRepository.save(newForumentrymodel);
			return "forward:/blogEntries";		
		}
		
		
	}
	
	// @Secured("ROLE_ADMIN")
	@RequestMapping("/deletePost")
	public String deletePost(Model model, @RequestParam int forumentryId) {//, 
			//@RequestParam String thread) { -->, thread=${thread}
		
		System.out.print("HALLO ICH BIN EIN ADMIN UND BERECHTIGT");
		System.out.print(forumentryId);
		
		
		
		// model.addAttribute("thread", thread);
		return "forward:/blogEntries";
	}
	
	
	
	// User verspeichern
	@RequestMapping(value = { "/registration" })
	public String getRegistration(@Valid UserModel newUserModel, BindingResult bindingResult, Model model) {
		findCoaches(model);
		model.addAttribute("user", newUserModel);
		
		return "registration";
	}
	
	@Transactional
	@PostMapping("/addUser")
	public String addUser(@Valid UserModel newUserModel, BindingResult bindingResult, Model model) {
	
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			
			System.out.print(newUserModel);
			
			model.addAttribute("user", newUserModel); //
							
			findCoaches(model);
			return "registration";
				
		}
		
		
		UserModel user = userQueryRepository.findByUserName(newUserModel.getUserName());
				
		if (user != null) {
			model.addAttribute("errorMessage", "Username already exists!");
			model.addAttribute("user", newUserModel); //
			findCoaches(model);
			return "registration";
		} 
			
		UserModel email = userQueryRepository.findByEMail(newUserModel.geteMail());
		if (email != null) {
			model.addAttribute("errorMessage", "E-Mail already exists!");
			model.addAttribute("user", newUserModel); //
			findCoaches(model);
			return "registration";
		}
		
		
		
		 
		 
		if (( newUserModel.getPassword().equals(newUserModel.getPasswordConfirmed()) ) && 
				newUserModel.getPassword().length() >= 6 ) {
			try {		
				
				RoleModel userRole = roleRepository.getRole("ROLE_USER");
								
				UserModel newUser = new UserModel(newUserModel.getFirstName(), newUserModel.getLastName(), 
						newUserModel.getUserName(), newUserModel.getBirthDate(), newUserModel.getGender(), 
						newUserModel.getHeight(), newUserModel.getWeight(), newUserModel.getCoach(), 
						newUserModel.geteMail(), 0, false, true, newUserModel.getPassword(), null);
				
				// newUserModel.setEnabled(true);
				
				newUser.encryptPassword();
				// newUser.addRoleModel(roleQueryRepository.findFirstRoleById(1));
				newUser.addRoleModel(userRole);
				
				System.out.print(newUser);
				userRepository.persist(newUser);
				
				System.out.print("ERFOLGREICH!");
				model.addAttribute("message", "New User " + newUser.getUserName() + " successfully added!");
				
				return "login";
			} 
			catch (Exception e) {
				model.addAttribute("user", newUserModel); //
				findCoaches(model);
				return "registration";
			}
			
		} else {
		
			System.out.print("LENGTH: " + newUserModel.getPassword().length());
			System.out.print("1. PASSWORD: " + newUserModel.getPassword());
			System.out.print("1. PASSWORD CONFIRMED: " + newUserModel.getPasswordConfirmed());
			
			
			model.addAttribute("errorMessage", "Password is not the same or it has to be at least six characters long!");
			model.addAttribute("user", newUserModel); //
			findCoaches(model);
			return "registration";
		
		}
		
		/*else if (newUserModel.getPassword() != newUserModel.getPasswordConfirmed()) {
			System.out.print("PASSWORD: " + newUserModel.getPassword());
			System.out.print("PASSWORD CONFIRMED: " + newUserModel.getPasswordConfirmed());

			model.addAttribute("errorMessage", "Password has to be the same!");
			model.addAttribute("user", newUserModel); //
			findCoaches(model);
			return "registration";
		} */ 
		
	} 
	


	
	
	//@{/userSettings1(userName=${user.userName})}
	@RequestMapping(value = { "/userSettings1" }, method = RequestMethod.GET)
	public String getUserSettings(Model model, @RequestParam String userName) {

		UserModel user = userQueryRepository.findByUserName(userName);

		if (user != null) {
			model.addAttribute("user", user);
			return "userSettings";
		} 
      /*
			 * else { model.addAttribute("errorMessage", "Couldn't find user " + userName);
			 * return "profile"; }
			 */
		return "forward:/profile";

	}

	@RequestMapping(value = { "/userSettings" }, method = RequestMethod.POST)
	public String editUser(@Valid UserModel changedUserModel, BindingResult bindingResult, Model model) {

		String[] partUserName = (changedUserModel.userName).split(",");


		UserModel user = userQueryRepository.findByUserName(partUserName[0]);
		// keine schöne lösung, wieso ist aber userName=MaMu,MaMu,??

		// * if (user == null) { model.addAttribute("errorMessage",
		// * "User does not exist!<br>"); }


		user.setFirstName(changedUserModel.getFirstName());
		user.setLastName(changedUserModel.getLastName());
		user.seteMail(changedUserModel.geteMail());
		user.setHeight(changedUserModel.getHeight());
		user.setWeight(changedUserModel.getWeight());

		user = userRepository.merge(user);
		
		
		//unser Ansatz um das Problem zu lï¿½sen. Jedoch funktioniert die zuweisung zu user nicht.
		
	
		// coach fehlt noch

		// Save a message for the web page

		model.addAttribute("message", "update succes by " + changedUserModel.toString());
		// model.addAttribute("user", user);
		return "forward:/profile";
	}

	/*@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	} */
}