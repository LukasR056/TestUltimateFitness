package at.fh.swenga.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import at.fh.swenga.model.DocumentModel;
import at.fh.swenga.model.ExerciseModel;

//import at.fh.swenga.repository.LogRepository;

import at.fh.swenga.model.ForumentryModel;
import at.fh.swenga.model.LogModel;
import at.fh.swenga.model.PictureModel;
import at.fh.swenga.model.RoleModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserPictureModel;
import at.fh.swenga.repository.DocumentRepository;
import at.fh.swenga.repository.ExerciseRepository;
import at.fh.swenga.repository.ForumentryRepository;
import at.fh.swenga.repository.LogRepository;
import at.fh.swenga.repository.PictureRepository;
import at.fh.swenga.repository.RoleQueryRepository;
import at.fh.swenga.repository.RoleRepository;
import at.fh.swenga.repository.UserPictureDao;
import at.fh.swenga.repository.UserPictureRepo;
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
	LogRepository logRepository;
	@Autowired
	PictureRepository pictureRepository;
	@Autowired
	UserPictureDao userPictureDao;
	@Autowired
	UserPictureRepo userPictureRepo;
	@Autowired
	RoleQueryRepository roleQueryRepository;
	@Autowired
	ForumentryRepository forumentryRepository;
	@Autowired
	DocumentRepository documentRepository;
	/*@Autowired
	MailSender mailSender;
	@Autowired
	SimpleMailMessage templateMessage; */

	@InitBinder
	public void initDateBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	public void findCoaches(Model model) {
		List<UserModel> coaches = null;
		coaches = userQueryRepository.findCoach();
		model.addAttribute("coaches", coaches);
	}

	@RequestMapping(value = { "/" })
	public String index(Model model, String name, String type) {

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

		// Date now = new Date();

		// List<UserModel> users = userRepository.getUsers();

		/*
		 * if (users.isEmpty()) { UserModel u1 = new UserModel("person", "test", "user",
		 * now, "w", 1.70, 70.5, null, "test@schwinger", 100, false, true, "password",
		 * null);
		 * 
		 * u1.encryptPassword(); u1.addRoleModel(userRole); userRepository.persist(u1);
		 * logRepository.persist(l1);
		 * 
		 * 
		 * UserModel u2 = new UserModel("person", "test", "admin", now, "m", 1.80, 80.7,
		 * null, "test@schwinge2r", 100, false, true, "password", null);
		 * 
		 * u2.encryptPassword(); u2.addRoleModel(userRole); u2.addRoleModel(coachRole);
		 * u2.addRoleModel(adminRole); userRepository.persist(u2);
		 * 
		 * logRepository.persist(l2); }
		 */

		return "login";

	}

	// fill Exercise
	@RequestMapping(value = "/fillEx")
	public String fillEx() {

		ExerciseModel exercise1 = new ExerciseModel("Flutter Kicks", "Stomach", null,
				"1. Start lying down, hands by your sides, with your lower back pressed flat on the floor. Keeping your core tight, lift legs slightly off the floor. 2. Pointing your toes, bring one foot up, then the other, and repeat so they're fluttering without feet ever touching the floor. Continue for 30 seconds.");
		ExerciseModel exercise2 = new ExerciseModel("Side-Planks", "Stomach", null,
				"1. Start lying on your side, elbow directly underneath your shoulder and palm flat on the floor. Stack your feet, then lift hips up so your body forms a straight line. Hold for 30 seconds, switch sides, repeat.");
		ExerciseModel exercise3 = new ExerciseModel("See Saw Press", "Shoulders", null,
				" Hold two dumbbells just behind your shoulders, palms facing forward. Look up and tilt your body to the left, extending your right arm straight above you. Lower the dumbbell and repeat on the other side to create a see-saw motion. ");
		ExerciseModel exercise4 = new ExerciseModel("Seated Lateral Raise", "Shoulders", null,
				"Sit on a bench and hold a dumbbell in each hand by your side. Raise both dumbbells to your side until they're shoulder height. Lower under control and repeat.");
		ExerciseModel exercise5 = new ExerciseModel("Regular push-ups","Chest",null,"This classic bodyweight exercise is excellent to start with as well as for keeping as a training staple in any full-body or upper-body workout. Make sure to use a wide grip, as this will work your chest muscles more than a narrow grip technique.");
		ExerciseModel exercise6 = new ExerciseModel("Incline push-ups","Chest",null,"If you find a standard push-up too challenging at first, then you can start with an incline push-up. The steeper the incline, the less body weight you will need to work push. This is also a good exercise to target your lower chest.");
		ExerciseModel exercise7 = new ExerciseModel("Decline push-ups","Chest",null,"What goes up, must come down. These push-ups will help you target your upper chest and deltoid muscles specifically. It will also add more of your body weight to the exercise than a standard push-up, thus making it harder.");
		ExerciseModel exercise8 = new ExerciseModel("Plyometric push-ups","Chest",null,"Are you ready to explode into action? These push-ups, can be executed in a variety of fun and fantastical way, think clap push-ups. These bursts of powerful plyometric movement will have your muscles firing on all cylinders.");
		ExerciseModel exercise10 = new ExerciseModel("Squat Jump","Legs",null,"With your feet hip-width apart, squat until your thighs are parallel to the floor, and then jump as high as you can. Allow your knees to bend 45 degrees when you land, pause in deep squat position for one full second, and then jump again.");
		ExerciseModel exercise11 = new ExerciseModel("Side Lunge","Legs",null,"Stand with your feet about twice shoulder-width apart. Keeping your right leg straight, push your hips back and to the left. Then bend your left knee and lower your body until your left thigh is parallel to the floor. Your feet should remain flat on the floor at all times. Pause for two seconds, and then return to the starting position. Complete all reps and switch sides.");
		ExerciseModel exercise12 = new ExerciseModel("Scissor Box Jump","Legs",null,"Place your left foot on a box or bench with your right foot on the floor. In one movement, jump up and switch leg positions in midair. At the bottom position, pause for one second before alternating to the other leg.");
		ExerciseModel exercise13 = new ExerciseModel("Single-Leg Hip Raise","Legs",null,"Lie faceup, arms out to your sides at 45-degree angles, left foot flat on the floor with that knee bent, and your right leg straight. Raise your right leg until it�s in line with your left thigh. Then squeeze your glutes and push your hips up�your lower back will elevate. Pause, and return to the starting position.");

		exerciseRepository.save(exercise1);
		exerciseRepository.save(exercise2);
		exerciseRepository.save(exercise3);
		exerciseRepository.save(exercise4);
		exerciseRepository.save(exercise4);
		exerciseRepository.save(exercise5);
		exerciseRepository.save(exercise6);
		exerciseRepository.save(exercise7);
		exerciseRepository.save(exercise8);
		exerciseRepository.save(exercise11);
		exerciseRepository.save(exercise10);
		exerciseRepository.save(exercise12);
		exerciseRepository.save(exercise13);

		return "profile";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {

		return "login";
	}

	@Transactional
	@PostMapping("/logout")
	public String handleLogout(Model model, Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) {
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		model.addAttribute("message", "You have been logged out successfully!");

		// return "login";
		return "redirect:/login?logout";

	}

	// loading the profil
	@RequestMapping(value = { "/profile" })
	public String getProfile(Model model, Authentication authentication) {

		UserModel user = null;

		String searchString = authentication.getName();

		user = userQueryRepository.findByUserName(searchString);

		Set<LogModel> logs = user.getLogs();

		// Liste mit Einträge für die Grafik für Javascript
		List<Double> heigtnumbers = new ArrayList<Double>();
		// int length =0;
		for (LogModel log : logs) {
			heigtnumbers.add(log.getHeight());
		}

		List<Double> weightnumbers = new ArrayList<Double>();
		for (LogModel log : logs) {
			weightnumbers.add(log.getWeight());
		}

		List<Double> bminumbers = new ArrayList<Double>();
		for (LogModel log : logs) {
			double weight = log.getWeight();
			double height = log.getHeight();

			double height2 = height * height;
			double bmi = weight / height2;// weight/height*height;

			bminumbers.add(bmi);
		}

		List<Integer> pointnumbers = new ArrayList<Integer>();
		for (LogModel log : logs) {
			pointnumbers.add(log.getPoints());
		}
	
		List<Double> heights7 = heigtnumbers.subList(Math.max(heigtnumbers.size() - 7, 0), heigtnumbers.size());
		List<Double> weights7 = weightnumbers.subList(Math.max(weightnumbers.size() - 7, 0), weightnumbers.size());
		List<Double> bmi7 = bminumbers.subList(Math.max(bminumbers.size() - 7, 0), bminumbers.size());
		List<Integer> points7 = pointnumbers.subList(Math.max(pointnumbers.size() - 7, 0), pointnumbers.size());

		// piediagram
		List<ExerciseModel> exercisesFromUser = user.getExercises();
		
		int stomachCounter = 0;
		int legCounter = 0;
		int shoulderCounter = 0;
		int chestCounter = 0;

		
		
		for (ExerciseModel exercise : exercisesFromUser) {
			
			if (exercise.getType().equals("Stomach"))
			{
				stomachCounter = stomachCounter +1;
			}
			if (exercise.getType().equals("Legs"))
			{
				legCounter = legCounter +1;
			}
			if (exercise.getType().equals("Shoulders"))
			{
				shoulderCounter = shoulderCounter +1;
			}
			if (exercise.getType().equals("Chest"))
			{
				chestCounter = chestCounter +1;
			}
			
		}

		List<Integer> counterList = new ArrayList<Integer>();
		counterList.add(stomachCounter);
		counterList.add(shoulderCounter);
		counterList.add(legCounter);
		counterList.add(chestCounter);
		
		model.addAttribute("user", user);
		model.addAttribute("logs", heigtnumbers.toString()); // keyword für die Liste in
		model.addAttribute("counterList",counterList);														// /scripts/app/app-blog-overiew.1.1.0.js
		model.addAttribute("weights", weightnumbers.toString());
		model.addAttribute("bmis", bminumbers.toString());
		model.addAttribute("heights7", heights7.toString());
		model.addAttribute("weights7", weights7.toString());
		model.addAttribute("bmi7", bmi7.toString());
		model.addAttribute("points7", points7.toString());

		return "profile";
	}

	// first Methode for exercise
	@RequestMapping(value = { "/exercise" })
	public String getExercise(Model model, Authentication authentication) {

		// ALLE Exercises von einem User
		UserModel user = null;

		String searchString = authentication.getName();

		user = userQueryRepository.findByUserName(searchString);

		model.addAttribute("user", user);
		model.addAttribute("exercises", user.getExercises());

		return "exercise";
	}

	// show new Exercises
	@RequestMapping(value = { "/showexercise" })
	public String showexercise(Model model, @RequestParam String searchString, Authentication authentication) {

		List<ExerciseModel> exercises = new ArrayList<ExerciseModel>();
		UserModel user = new UserModel();
		String searchStringname = authentication.getName();
		user = userQueryRepository.findByUserName(searchStringname);

		System.out.print(searchString);

		switch (searchString) {
		case "AllExercises":
			exercises = exerciseRepository.findAll();
			break;

		default:
			exercises = exerciseRepository.findByType(searchString);

		}

		model.addAttribute("user", user);
		model.addAttribute("exercises", user.getExercises());
		model.addAttribute("allexercises", exercises);

		return "exercise";
	}

	// get new exercise for the user
	@RequestMapping(value = { "/addexercise" })
	public String addExercise(Model model, @RequestParam int id, Authentication authentication) {
		UserModel user = null;

		String searchStringname = authentication.getName();

		user = userQueryRepository.findByUserName(searchStringname);

		ExerciseModel newExercise = new ExerciseModel();
		newExercise = exerciseRepository.findById(id);

		List<ExerciseModel> exercisesFromUser = new ArrayList<ExerciseModel>();
		exercisesFromUser = user.getExercises();
		String successMessage = "You have a new exercise!";
		String failMessage = "Exercise is already in your list.";

		if (exercisesFromUser.contains(newExercise)) {

			model.addAttribute("message", failMessage);
		} else {
			user.addExercise(newExercise);
			userRepository.merge(user);
			model.addAttribute("message", successMessage);
		}

		model.addAttribute("user", user);
		model.addAttribute("exercises", user.getExercises());

		return "exercise";
	}

	// delete exercise from user
	@RequestMapping(value = { "deleteexercise" })
	public String deleteExercise(Model model, @RequestParam int id, Authentication authentication) {
		// Get User
		UserModel user = new UserModel();
		String searchStringname = authentication.getName();
		user = userQueryRepository.findByUserName(searchStringname);

		List<ExerciseModel> exerciseFromUser = new ArrayList<ExerciseModel>();
		exerciseFromUser = user.getExercises();

		ExerciseModel deleteExercise = new ExerciseModel();
		deleteExercise = exerciseRepository.findById(id);

		if (exerciseFromUser.size() == 1) {
			user.setExercises(new ArrayList<ExerciseModel>());
		} else {
			exerciseFromUser.remove(deleteExercise);
			user.setExercises(exerciseFromUser);
		}

		String successMessage = "You have delete on of your exercises!";
		userRepository.merge(user);
		model.addAttribute("exercises", user.getExercises());
		model.addAttribute("user", user);
		model.addAttribute("message", successMessage);

		return "exercise";
	}

	// show picture
	@RequestMapping(value = { "/picture" })
	public String getPicture(Model model, Authentication authentication) {

		// get logged User
		String authenName = authentication.getName();
		UserModel user = userQueryRepository.findByUserName(authenName);

		// all Pics of User
		List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
		bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "bronze");
		List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
		silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "silber");
		List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
		goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "gold");

		// allPics
		List<PictureModel> bronzePics = new ArrayList<PictureModel>();
		bronzePics = pictureRepository.findByLevel("bronze");
		List<PictureModel> silverPics = new ArrayList<PictureModel>();
		silverPics = pictureRepository.findByLevel("silber");
		List<PictureModel> goldPics = new ArrayList<PictureModel>();
		goldPics = pictureRepository.findByLevel("gold");

		// generate all index List
		List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel bronzePicOfUser : bronzePicsOfUser) {
			bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
		}
		List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel silverPicOfUser : silverPicsOfUser) {
			silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
		}
		List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel goldPicOfUser : goldPicsOfUser) {
			goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
		}

		// missing Picture List
		List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();

		for (PictureModel bronzePic : bronzePics) {
			if (bronzePicsOfUserIndexList.contains(bronzePic.getPictureId())) {

			} else {
				missingPicsBronze.add(bronzePic);
			}

		}
		for (PictureModel goldPic : goldPics) {
			if (goldPicsOfUserIndexList.contains(goldPic.getPictureId())) {

			} else {
				missingPicsGold.add(goldPic);
			}

		}
		for (PictureModel silverPic : silverPics) {
			if (silverPicsOfUserIndexList.contains(silverPic.getPictureId())) {

			} else {
				missingPicsSilver.add(silverPic);
			}

		}

		model.addAttribute("bronzePicOfUser", bronzePicsOfUser);
		model.addAttribute("silverPicOfUser", silverPicsOfUser);
		model.addAttribute("goldPicOfUser", goldPicsOfUser);

		System.out.print(missingPicsBronze);
		System.out.print(missingPicsSilver);
		System.out.print(missingPicsGold);

		model.addAttribute("missingBronzePics", missingPicsBronze);
		model.addAttribute("missingSilverPics", missingPicsSilver);
		model.addAttribute("missingGoldPics", missingPicsGold);
		model.addAttribute("user", user);

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
	public String newPost(@Valid ForumentryModel newForumentryModel, BindingResult bindingResult, Model model,
			@RequestParam String thread, Authentication authentication) {
		UserModel user = null;

		user = userQueryRepository.findByUserName(authentication.getName());

		model.addAttribute("forumentry", newForumentryModel);
		model.addAttribute("user", user);
		model.addAttribute("thread", thread);
		return "newPost";

	}

	@RequestMapping(value = { "/settings" })
	public String getSettings(Model model) {
		List<UserModel> userId = userQueryRepository.findAll();
		model.addAttribute("users", userId);
		return "settings";
	}

	// @{/safePost(thread=${thread})}
	@Transactional
	@PostMapping("/safePost")
	public String safePost(@Valid ForumentryModel newForumentrymodel, BindingResult bindingResult, Model model,
			@RequestParam String thread, Authentication authentication) {

		model.addAttribute("thread", thread);
		newForumentrymodel.setThread(thread);
		newForumentrymodel.setCreateDate(new Date());

		UserModel activeUser = userQueryRepository.findByUserName(authentication.getName());
		newForumentrymodel.setUser(activeUser);

		// System.out.print(activeUser);
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

	@RequestMapping("/editPost")
	public String editPost(Model model, @RequestParam int forumentryId, @RequestParam String thread) {
		Optional<ForumentryModel> forumentry = forumentryRepository.findById(forumentryId);

		model.addAttribute("thread", thread);
		model.addAttribute("forumentry", forumentry);

		return "newPost";
	}

	// @Secured("ROLE_ADMIN")
	@RequestMapping("/deletePost")
	public String deletePost(Model model, @RequestParam int forumentryId) {// ,
		// @RequestParam String thread) { -->, thread=${thread}

		System.out.print("HALLO ICH BIN EIN ADMIN UND BERECHTIGT");
		System.out.print(forumentryId);

		forumentryRepository.deleteById(forumentryId);

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

		if ((newUserModel.getPassword().equals(newUserModel.getPasswordConfirmed()))
				&& newUserModel.getPassword().length() >= 6) {
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
				
				//Log
				Date date = new Date();
				LogModel log = new LogModel(newUser, newUser.getHeight(), newUser.getWeight(), newUser.getPoints(), date);
				logRepository.save(log);
				

				System.out.print("ERFOLGREICH!");
				model.addAttribute("message", "New User " + newUser.getUserName() + " successfully added!");

				return "login";
			} catch (Exception e) {
				model.addAttribute("user", newUserModel); //
				findCoaches(model);
				return "registration";
			}

		} else {

			System.out.print("LENGTH: " + newUserModel.getPassword().length());
			System.out.print("1. PASSWORD: " + newUserModel.getPassword());
			System.out.print("1. PASSWORD CONFIRMED: " + newUserModel.getPasswordConfirmed());

			model.addAttribute("errorMessage",
					"Password is not the same or it has to be at least six characters long!");
			model.addAttribute("user", newUserModel); //
			findCoaches(model);
			return "registration";

		}

		/*
		 * else if (newUserModel.getPassword() != newUserModel.getPasswordConfirmed()) {
		 * System.out.print("PASSWORD: " + newUserModel.getPassword());
		 * System.out.print("PASSWORD CONFIRMED: " +
		 * newUserModel.getPasswordConfirmed());
		 * 
		 * model.addAttribute("errorMessage", "Password has to be the same!");
		 * model.addAttribute("user", newUserModel); // findCoaches(model); return
		 * "registration"; }
		 */

	}

	// @{/userSettings1(userName=${user.userName})}
	@Transactional
	@RequestMapping(value = { "/userSettings1"})
	public String getUserSettings(Model model,Authentication authentication) {

		UserModel user = userQueryRepository.findByUserName(authentication.getName());

		List<UserModel> coaches = new ArrayList<UserModel>();
		coaches = userQueryRepository.findCoach();
		
		System.out.print("COOOOOOOOOOOOOOOOOOOOOOOACHEEEES" + coaches);
		
		model.addAttribute("coaches", coaches);

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

	@RequestMapping(value = "/fillPics")
	public String fillPics(Model model) {
		PictureModel p1 = new PictureModel(1, "bild1_part1", "bronze");
		pictureRepository.save(p1);

		PictureModel p2 = new PictureModel(2, "bild1_part2", "bronze");
		pictureRepository.save(p2);

		PictureModel p3 = new PictureModel(3, "bild1_part3", "bronze");
		pictureRepository.save(p3);

		PictureModel p4 = new PictureModel(4, "bild1_part4", "bronze");
		pictureRepository.save(p4);

		PictureModel p5 = new PictureModel(5, "bild1_part5", "bronze");
		pictureRepository.save(p5);

		PictureModel p6 = new PictureModel(6, "bild2_part1", "silber");
		pictureRepository.save(p6);

		PictureModel p7 = new PictureModel(7, "bild2_part2", "silber");
		pictureRepository.save(p7);

		PictureModel p8 = new PictureModel(8, "bild2_part3", "silber");
		pictureRepository.save(p8);

		PictureModel p9 = new PictureModel(9, "bild3_part1", "gold");
		pictureRepository.save(p9);

		PictureModel p10 = new PictureModel(10, "bild3_part2", "gold");
		pictureRepository.save(p10);

		return "picture";
	}

	// for the gold pac
	@RequestMapping(value = "/goldPack")
	public String goldPack(Model model, Authentication authentication) {

		// getting User
		String searchString = authentication.getName();
		UserModel user = userQueryRepository.findByUserName(searchString);
		
		String message;
		
		if(user.getPoints() < 300) {
			System.out.print("ZU WENIGE PUNKTE");
			message = "Not enough points";
		} else {
			
			user.setPoints(user.getPoints()-300);
			message = "Gold Pack bought successfully";

			// getting all pics of one level
			List<PictureModel> goldPics = new ArrayList<PictureModel>();
			goldPics = pictureRepository.findByLevel("gold");
			List<PictureModel> silverPics = new ArrayList<PictureModel>();
			silverPics = pictureRepository.findByLevel("silber");
			List<PictureModel> bronzePics = new ArrayList<PictureModel>();
			bronzePics = pictureRepository.findByLevel("bronze");
	
			// get the size for the id
			List<UserPictureModel> allModels = userPictureRepo.findAll();
	
			// get random numbers
			Random randGold = new Random();
			int randomGoldPic = randGold.nextInt(goldPics.size());
	
			Random randSilver = new Random();
			int randomSilverPic = randSilver.nextInt(silverPics.size());
	
			Random randBronze1 = new Random();
			int randomBronzePic1 = randBronze1.nextInt(bronzePics.size());
	
			Random randBronze2 = new Random();
			int randomBronzePic2 = randBronze2.nextInt(bronzePics.size());
	
			// get Pac
			ArrayList<PictureModel> picPack = new ArrayList<PictureModel>();
			picPack.add(goldPics.get(randomGoldPic));
			picPack.add(silverPics.get(randomSilverPic));
			picPack.add(bronzePics.get(randomBronzePic1));
			picPack.add(bronzePics.get(randomBronzePic2));
	
			// all Pictures from the logged user
			// List<UserPictureModel> ownedPictures = new ArrayList<UserPictureModel>();
			List<UserPictureModel> ownedPictures = userPictureRepo.findByUser(user);
	
			// List of ownedPictureIds
			List<Integer> ownedPictureIndexList = new ArrayList<Integer>();
			for (UserPictureModel ownedPicture : ownedPictures) {
				ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
			}
	
			// compare ownedPics with new pics of Pac
			for (PictureModel pic : picPack) {
	
				// get the size for the id
				allModels = userPictureRepo.findAll();
				// if fürs update
				if (ownedPictureIndexList.contains(pic.getPictureId())) {
					UserPictureModel updateModel = userPictureRepo.findByUserAndPicture(user, pic);
					updateModel.setAmount(updateModel.getAmount() + 1);
					userPictureDao.merge(updateModel);
					System.out.print("in update");
				} else {
					UserPictureModel newPicForUser = new UserPictureModel();
					newPicForUser.setId(allModels.size() + 1);
					newPicForUser.setUser(user);
					newPicForUser.setPicture(pic);
					newPicForUser.setAmount(1);
					userPictureRepo.save(newPicForUser);
	
					System.out.print("in save");
	
				}
	
				ownedPictures = userPictureRepo.findByUser(user);
				// List of ownedPictureIds update
				ownedPictureIndexList = new ArrayList<Integer>();
				for (UserPictureModel ownedPicture : ownedPictures) {
					ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
				}
			}
	
			// generate the model for the frontend
	
			// get logged User
	
			// all Pics of User
			List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
			bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "bronze");
			List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
			silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "silber");
			List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
			goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "gold");
	
			// allPics
			bronzePics = new ArrayList<PictureModel>();
			bronzePics = pictureRepository.findByLevel("bronze");
			silverPics = new ArrayList<PictureModel>();
			silverPics = pictureRepository.findByLevel("silber");
			goldPics = new ArrayList<PictureModel>();
			goldPics = pictureRepository.findByLevel("gold");
	
			// generate all index List
			List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
			for (UserPictureModel bronzePicOfUser : bronzePicsOfUser) {
				bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
			}
			List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
			for (UserPictureModel silverPicOfUser : silverPicsOfUser) {
				silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
			}
			List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
			for (UserPictureModel goldPicOfUser : goldPicsOfUser) {
				goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
			}
	
			// missing Picture List
			List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
			List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
			List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();
	
			for (PictureModel bronzePic : bronzePics) {
				if (bronzePicsOfUserIndexList.contains(bronzePic.getPictureId())) {
	
				} else {
					missingPicsBronze.add(bronzePic);
				}
	
			}
			for (PictureModel goldPic : goldPics) {
				if (goldPicsOfUserIndexList.contains(goldPic.getPictureId())) {
	
				} else {
					missingPicsGold.add(goldPic);
				}
	
			}
			for (PictureModel silverPic : silverPics) {
				if (silverPicsOfUserIndexList.contains(silverPic.getPictureId())) {
	
				} else {
					missingPicsSilver.add(silverPic);
				}
	
			}
	
			model.addAttribute("bronzePicOfUser", bronzePicsOfUser);
			model.addAttribute("silverPicOfUser", silverPicsOfUser);
			model.addAttribute("goldPicOfUser", goldPicsOfUser);
	
			System.out.print(missingPicsBronze);
			System.out.print(missingPicsSilver);
			System.out.print(missingPicsGold);
	
			model.addAttribute("missingBronzePics", missingPicsBronze);
			model.addAttribute("missingSilverPics", missingPicsSilver);
			model.addAttribute("missingGoldPics", missingPicsGold);
			model.addAttribute("user", user);
		
		}
		
		model.addAttribute("pack", message);

		return "picture";

	}
	

	// for the gold Reward
	@RequestMapping(value = "/goldReward")
	public String goldReward(Model model, Authentication authentication) {

		// getting User
		String searchString = authentication.getName();
		UserModel user = userQueryRepository.findByUserName(searchString);
		
		String rewardMessage = null;
		
		//get his goldPics
		List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
		goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"gold");
		
		List<PictureModel>goldPics1 = new ArrayList<PictureModel>();
		goldPics1 = pictureRepository.findByLevel("gold");
		
		List<Integer> goldPicsIndexList1 = new ArrayList<Integer>();
		for (PictureModel goldPic: goldPics1)
		{
			goldPicsIndexList1.add(goldPic.getPictureId());
		}
		
		Collections.sort(goldPicsIndexList1);
		
		List<Integer> goldPicsOfUserIndexList1 = new ArrayList<Integer>();
		for (UserPictureModel goldPicOfUser: goldPicsOfUser)
		{
			goldPicsOfUserIndexList1.add(goldPicOfUser.getPicture().getPictureId());
		}
		
		Collections.sort(goldPicsOfUserIndexList1);
		
		//vergleicht 2 Listen (List of PictureIds)
		if (goldPicsIndexList1.equals(goldPicsOfUserIndexList1)) {
			
			
			
			List<UserPictureModel> kaufUsergold = new ArrayList<UserPictureModel>();
			kaufUsergold = userPictureRepo.findByUserAndPictureLevel(user, "gold");
			
			int i = 1;
			
			for( UserPictureModel eintrag : kaufUsergold)
			{
				if (eintrag.getAmount()>0) {
					
				} else {
					i++;
					rewardMessage = "Not enough pics";
				}
			}
			
			if(i==1) {
				for( UserPictureModel eintrag : kaufUsergold)
				{
					eintrag.setAmount(eintrag.getAmount()-1);
					userPictureRepo.save(eintrag);
					rewardMessage = "Succesfully got gold reward";
				}
			}
			
			System.out.print("GLEICH");
		} else {
			System.out.print("NICHT GLEICH");
			rewardMessage = "Not enough pics";
		}
		
		
		//generate the model for the frontend

				// all Pics of User
				List<UserPictureModel>bronzePicsOfUser = new ArrayList<UserPictureModel>();
				bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "bronze");
				List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
				silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "silber");
				
				goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "gold");

				// allPics
				List<PictureModel> bronzePics = new ArrayList<PictureModel>();
				bronzePics = pictureRepository.findByLevel("bronze");
				List<PictureModel> silverPics = new ArrayList<PictureModel>();
				silverPics = pictureRepository.findByLevel("silber");
				List<PictureModel> goldPics = new ArrayList<PictureModel>();
				goldPics = pictureRepository.findByLevel("gold");

				// generate all index List
				List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel bronzePicOfUser : bronzePicsOfUser) {
					bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
				}
				List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel silverPicOfUser : silverPicsOfUser) {
					silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
				}
				List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel goldPicOfUser : goldPicsOfUser) {
					goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
				}

				// missing Picture List
				List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
				List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
				List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();

				for (PictureModel bronzePic : bronzePics) {
					if (bronzePicsOfUserIndexList.contains(bronzePic.getPictureId())) {

					} else {
						missingPicsBronze.add(bronzePic);
					}

				}
				for (PictureModel goldPic : goldPics) {
					if (goldPicsOfUserIndexList.contains(goldPic.getPictureId())) {

					} else {
						missingPicsGold.add(goldPic);
					}

				}
				for (PictureModel silverPic : silverPics) {
					if (silverPicsOfUserIndexList.contains(silverPic.getPictureId())) {

					} else {
						missingPicsSilver.add(silverPic);
					}

				}

				model.addAttribute("bronzePicOfUser", bronzePicsOfUser);
				model.addAttribute("silverPicOfUser", silverPicsOfUser);
				model.addAttribute("goldPicOfUser", goldPicsOfUser);

				/*
				 * System.out.print(missingPicsBronze); System.out.print(missingPicsSilver);
				 * System.out.print(missingPicsGold);
				 */

				model.addAttribute("missingBronzePics", missingPicsBronze);
				model.addAttribute("missingSilverPics", missingPicsSilver);
				model.addAttribute("missingGoldPics", missingPicsGold);
				model.addAttribute("user", user);
				
				model.addAttribute("pack", rewardMessage);

				
			//	sendMail(user, "REWARD-GOLD", rewardMessage);
				
				return "picture";


	}

	// for the silver pac
	@RequestMapping(value = "/silverPack")
	public String silverPack(Model model, Authentication authentication) {

		// getting User
		String searchString = authentication.getName();
		UserModel user = userQueryRepository.findByUserName(searchString);
		
		String message;
		
		if(user.getPoints() < 150) {
			System.out.print("ZU WENIGE PUNKTE");
			message = "Not enough points";
		} else {
			
			user.setPoints(user.getPoints()-150);
			message = "Silver Pack bought successfully";

		// getting all pics of one level
		List<PictureModel> silverPics = new ArrayList<PictureModel>();
		silverPics = pictureRepository.findByLevel("silber");
		List<PictureModel> bronzePics = new ArrayList<PictureModel>();
		bronzePics = pictureRepository.findByLevel("bronze");

		// get the size for the id
		List<UserPictureModel> allModels = userPictureRepo.findAll();

		// get random numbers
		Random randSilver = new Random();
		int randomSilverPic = randSilver.nextInt(silverPics.size());

		Random randBronze1 = new Random();
		int randomBronzePic1 = randBronze1.nextInt(bronzePics.size());

		Random randBronze2 = new Random();
		int randomBronzePic2 = randBronze2.nextInt(bronzePics.size());

		// get Pac
		ArrayList<PictureModel> picPack = new ArrayList<PictureModel>();
		picPack.add(silverPics.get(randomSilverPic));
		picPack.add(bronzePics.get(randomBronzePic1));
		picPack.add(bronzePics.get(randomBronzePic2));

		// all Pictures from the logged user
		// List<UserPictureModel> ownedPictures = new ArrayList<UserPictureModel>();
		List<UserPictureModel> ownedPictures = userPictureRepo.findByUser(user);

		// List of ownedPictureIds
		List<Integer> ownedPictureIndexList = new ArrayList<Integer>();
		for (UserPictureModel ownedPicture : ownedPictures) {
			ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
		}

		// compare ownedPics with new pics of Pac
		for (PictureModel pic : picPack) {

			// get the size for the id
			allModels = userPictureRepo.findAll();
			// if fürs update
			if (ownedPictureIndexList.contains(pic.getPictureId())) {
				UserPictureModel updateModel = userPictureRepo.findByUserAndPicture(user, pic);
				updateModel.setAmount(updateModel.getAmount() + 1);
				userPictureDao.merge(updateModel);
				System.out.print("in update");
			} else {
				UserPictureModel newPicForUser = new UserPictureModel();
				newPicForUser.setId(allModels.size() + 1);
				newPicForUser.setUser(user);
				newPicForUser.setPicture(pic);
				newPicForUser.setAmount(1);
				userPictureRepo.save(newPicForUser);

				System.out.print("in save");

			}
			ownedPictures = userPictureRepo.findByUser(user);
			// List of ownedPictureIds update
			ownedPictureIndexList = new ArrayList<Integer>();
			for (UserPictureModel ownedPicture : ownedPictures) {
				ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
			}
		}

		// generate the model for the frontend

		// get logged User

		// all Pics of User
		List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
		bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "bronze");
		List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
		silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "silber");
		List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
		goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "gold");

		// allPics
		bronzePics = new ArrayList<PictureModel>();
		bronzePics = pictureRepository.findByLevel("bronze");
		silverPics = new ArrayList<PictureModel>();
		silverPics = pictureRepository.findByLevel("silber");
		List<PictureModel> goldPics = new ArrayList<PictureModel>();
		goldPics = pictureRepository.findByLevel("gold");

		// generate all index List
		List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel bronzePicOfUser : bronzePicsOfUser) {
			bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
		}
		List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel silverPicOfUser : silverPicsOfUser) {
			silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
		}
		List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel goldPicOfUser : goldPicsOfUser) {
			goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
		}

		// missing Picture List
		List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();

		for (PictureModel bronzePic : bronzePics) {
			if (bronzePicsOfUserIndexList.contains(bronzePic.getPictureId())) {

			} else {
				missingPicsBronze.add(bronzePic);
			}

		}
		for (PictureModel goldPic : goldPics) {
			if (goldPicsOfUserIndexList.contains(goldPic.getPictureId())) {

			} else {
				missingPicsGold.add(goldPic);
			}

		}
		for (PictureModel silverPic : silverPics) {
			if (silverPicsOfUserIndexList.contains(silverPic.getPictureId())) {

			} else {
				missingPicsSilver.add(silverPic);
			}

		}

		model.addAttribute("bronzePicOfUser", bronzePicsOfUser);
		model.addAttribute("silverPicOfUser", silverPicsOfUser);
		model.addAttribute("goldPicOfUser", goldPicsOfUser);

		System.out.print(missingPicsBronze);
		System.out.print(missingPicsSilver);
		System.out.print(missingPicsGold);

		model.addAttribute("missingBronzePics", missingPicsBronze);
		model.addAttribute("missingSilverPics", missingPicsSilver);
		model.addAttribute("missingGoldPics", missingPicsGold);
		model.addAttribute("user", user);

		}
		
		model.addAttribute("pack", message);
		
		return "picture";

	}
	
	// for the silver Reward
		@RequestMapping(value = "/silverReward")
		public String silverReward(Model model, Authentication authentication) {

			// getting User
			String searchString = authentication.getName();
			UserModel user = userQueryRepository.findByUserName(searchString);
			
			String rewardMessage = null;
			
			//get his silverPics
			List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
			silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"silber");
			
			List<PictureModel>silverPics1 = new ArrayList<PictureModel>();
			silverPics1 = pictureRepository.findByLevel("silber");
			
			List<Integer> silverPicsIndexList1 = new ArrayList<Integer>();
			for (PictureModel silverPic: silverPics1)
			{
				silverPicsIndexList1.add(silverPic.getPictureId());
			}
			
			Collections.sort(silverPicsIndexList1);
			
			List<Integer> silverPicsOfUserIndexList1 = new ArrayList<Integer>();
			for (UserPictureModel silverPicOfUser: silverPicsOfUser)
			{
				silverPicsOfUserIndexList1.add(silverPicOfUser.getPicture().getPictureId());
			}
			
			Collections.sort(silverPicsOfUserIndexList1);
			
			//Vergleih funktioniert, kaufUserBronze funktioniert auch, aber das Speichern der Models in die DB funktioniert noch nicht
			if (silverPicsIndexList1.equals(silverPicsOfUserIndexList1)) {
				
				List<UserPictureModel> kaufUserSilver = new ArrayList<UserPictureModel>();
				kaufUserSilver = userPictureRepo.findByUserAndPictureLevel(user, "silber");
				
				int i = 1;
				
				for( UserPictureModel eintrag : kaufUserSilver)
				{
					if (eintrag.getAmount()>0) {
						
					} else {
						i++;
						rewardMessage = "Not enough pics";
					}
				}
				
				if(i==1) {
					for( UserPictureModel eintrag : kaufUserSilver)
					{
						eintrag.setAmount(eintrag.getAmount()-1);
						userPictureRepo.save(eintrag);
						rewardMessage = "Succesfully got silver reward";
					}
				}
				System.out.print("GLEICH");
			} else {
				System.out.print("NICHT GLEICH");
				rewardMessage = "Not enough pics";
			}
			
			//generate the model for the frontend

			// all Pics of User
			List<UserPictureModel>bronzePicsOfUser = new ArrayList<UserPictureModel>();
			bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "bronze");
			
			silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "silber");
			List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
			goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "gold");

			// allPics
			List<PictureModel> bronzePics = new ArrayList<PictureModel>();
			bronzePics = pictureRepository.findByLevel("bronze");
			List<PictureModel> silverPics = new ArrayList<PictureModel>();
			silverPics = pictureRepository.findByLevel("silber");
			List<PictureModel> goldPics = new ArrayList<PictureModel>();
			goldPics = pictureRepository.findByLevel("gold");

			// generate all index List
			List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
			for (UserPictureModel bronzePicOfUser : bronzePicsOfUser) {
				bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
			}
			List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
			for (UserPictureModel silverPicOfUser : silverPicsOfUser) {
				silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
			}
			List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
			for (UserPictureModel goldPicOfUser : goldPicsOfUser) {
				goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
			}

			// missing Picture List
			List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
			List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
			List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();

			for (PictureModel bronzePic : bronzePics) {
				if (bronzePicsOfUserIndexList.contains(bronzePic.getPictureId())) {

				} else {
					missingPicsBronze.add(bronzePic);
				}

			}
			for (PictureModel goldPic : goldPics) {
				if (goldPicsOfUserIndexList.contains(goldPic.getPictureId())) {

				} else {
					missingPicsGold.add(goldPic);
				}

			}
			for (PictureModel silverPic : silverPics) {
				if (silverPicsOfUserIndexList.contains(silverPic.getPictureId())) {

				} else {
					missingPicsSilver.add(silverPic);
				}

			}

			model.addAttribute("bronzePicOfUser", bronzePicsOfUser);
			model.addAttribute("silverPicOfUser", silverPicsOfUser);
			model.addAttribute("goldPicOfUser", goldPicsOfUser);

			/*
			 * System.out.print(missingPicsBronze); System.out.print(missingPicsSilver);
			 * System.out.print(missingPicsGold);
			 */

			model.addAttribute("missingBronzePics", missingPicsBronze);
			model.addAttribute("missingSilverPics", missingPicsSilver);
			model.addAttribute("missingGoldPics", missingPicsGold);
			model.addAttribute("user", user);
			
			model.addAttribute("pack", rewardMessage);
			//sendMail(user, "REWARD-SILVER", rewardMessage);
			return "picture";


		}

	// for the bronze pac
	@RequestMapping(value = "/bronzePack")
	public String bronzePack(Model model, Authentication authentication) {

		// getting User
		String searchString = authentication.getName();
		UserModel user = userQueryRepository.findByUserName(searchString);
		
		String message;

		if(user.getPoints() < 100) {
			System.out.print("ZU WENIGE PUNKTE");
			message = "Not enough points";
			
		} else {
			
			user.setPoints(user.getPoints()-100);
			message = "Bronze Pack bought successfully";
		
		// getting all pics of one level
		List<PictureModel> bronzePics = new ArrayList<PictureModel>();
		bronzePics = pictureRepository.findByLevel("bronze");

		// get the size for the id
		List<UserPictureModel> allModels = userPictureRepo.findAll();

		// get random numbers
		Random randBronze1 = new Random();
		int randomBronzePic1 = randBronze1.nextInt(bronzePics.size());

		Random randBronze2 = new Random();
		int randomBronzePic2 = randBronze2.nextInt(bronzePics.size());

		// get Pac
		ArrayList<PictureModel> picPack = new ArrayList<PictureModel>();
		picPack.add(bronzePics.get(randomBronzePic1));
		picPack.add(bronzePics.get(randomBronzePic2));

		// all Pictures from the logged user
		// List<UserPictureModel> ownedPictures = new ArrayList<UserPictureModel>();
		List<UserPictureModel> ownedPictures = userPictureRepo.findByUser(user);

		// List of ownedPictureIds
		List<Integer> ownedPictureIndexList = new ArrayList<Integer>();
		for (UserPictureModel ownedPicture : ownedPictures) {
			ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
		}

		// compare ownedPics with new pics of Pac
		for (PictureModel pic : picPack) {

			// get the size for the id
			allModels = userPictureRepo.findAll();
			// if fürs update
			if (ownedPictureIndexList.contains(pic.getPictureId())) {
				UserPictureModel updateModel = userPictureRepo.findByUserAndPicture(user, pic);
				updateModel.setAmount(updateModel.getAmount() + 1);
				userPictureDao.merge(updateModel);
				System.out.print("in update");
			} else {
				UserPictureModel newPicForUser = new UserPictureModel();
				newPicForUser.setId(allModels.size() + 1);
				newPicForUser.setUser(user);
				newPicForUser.setPicture(pic);
				newPicForUser.setAmount(1);
				userPictureRepo.save(newPicForUser);

				System.out.print("in save");

			}
			ownedPictures = userPictureRepo.findByUser(user);
			// List of ownedPictureIds update
			ownedPictureIndexList = new ArrayList<Integer>();
			for (UserPictureModel ownedPicture : ownedPictures) {
				ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
			}
		}

		// generate the model for the frontend

		// get logged User

		// all Pics of User
		List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
		bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "bronze");
		List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
		silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "silber");
		List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
		goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "gold");

		// allPics
		bronzePics = new ArrayList<PictureModel>();
		bronzePics = pictureRepository.findByLevel("bronze");
		List<PictureModel> silverPics = new ArrayList<PictureModel>();
		silverPics = pictureRepository.findByLevel("silber");
		List<PictureModel> goldPics = new ArrayList<PictureModel>();
		goldPics = pictureRepository.findByLevel("gold");

		// generate all index List
		List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel bronzePicOfUser : bronzePicsOfUser) {
			bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
		}
		List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel silverPicOfUser : silverPicsOfUser) {
			silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
		}
		List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel goldPicOfUser : goldPicsOfUser) {
			goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
		}

		// missing Picture List
		List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();

		for (PictureModel bronzePic : bronzePics) {
			if (bronzePicsOfUserIndexList.contains(bronzePic.getPictureId())) {

			} else {
				missingPicsBronze.add(bronzePic);
			}

		}
		for (PictureModel goldPic : goldPics) {
			if (goldPicsOfUserIndexList.contains(goldPic.getPictureId())) {

			} else {
				missingPicsGold.add(goldPic);
			}

		}
		for (PictureModel silverPic : silverPics) {
			if (silverPicsOfUserIndexList.contains(silverPic.getPictureId())) {

			} else {
				missingPicsSilver.add(silverPic);
			}

		}
		
		model.addAttribute("bronzePicOfUser", bronzePicsOfUser);
		model.addAttribute("silverPicOfUser", silverPicsOfUser);
		model.addAttribute("goldPicOfUser", goldPicsOfUser);

		System.out.print(missingPicsBronze);
		System.out.print(missingPicsSilver);
		System.out.print(missingPicsGold);
		
		model.addAttribute("missingBronzePics", missingPicsBronze);
		model.addAttribute("missingSilverPics", missingPicsSilver);
		model.addAttribute("missingGoldPics", missingPicsGold);
		model.addAttribute("user", user);
		
		}

		model.addAttribute("pack", message);
		
	
		return "picture";

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

		Date date = new Date();

		// Hier werden die Änderungen eines Users in den Log eingetragen
		LogModel log = new LogModel(user, user.getHeight(), user.getWeight(), user.getPoints(), date);
		System.out.println(log);
		// logRepository.save(log);
		// log.setUser(user);
		// user.addLogModel(log);
		logRepository.save(log);

		// unser Ansatz um das Problem zu l�sen. Jedoch funktioniert die zuweisung zu
		// user nicht.

		// unser Ansatz um das Problem zu lï¿½sen. Jedoch funktioniert die zuweisung
		// zu user nicht.

		// coach fehlt noch

		// Save a message for the web page

		
		
		model.addAttribute("message", "Profile has been updated successfully");
		
		// model.addAttribute("user", user);
		return "forward:/profile";
	}

	//bronze Reward
		@RequestMapping(value = {"/bronzeReward"})
		public String bronzeReward(Model model, Authentication authentication) {
			
			String authenname = authentication.getName();
			UserModel user = userQueryRepository.findByUserName(authenname);
			
			String rewardMessage = null;
			
			//get his bronzePics
			List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
			bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"bronze");
			
			List<PictureModel>bronzePics1 = new ArrayList<PictureModel>();
			bronzePics1 = pictureRepository.findByLevel("bronze");
			
			List<Integer> bronzePicsIndexList1 = new ArrayList<Integer>();
			for (PictureModel bronzePic: bronzePics1)
			{
				bronzePicsIndexList1.add(bronzePic.getPictureId());
			}
			
			Collections.sort(bronzePicsIndexList1);
			
			List<Integer> bronzePicsOfUserIndexList1 = new ArrayList<Integer>();
			for (UserPictureModel bronzePicOfUser: bronzePicsOfUser)
			{
				bronzePicsOfUserIndexList1.add(bronzePicOfUser.getPicture().getPictureId());
			}
			
			Collections.sort(bronzePicsOfUserIndexList1);
			
			
			
			
			//Vergleih funktioniert, kaufUserBronze funktioniert auch, aber das Speichern der Models in die DB funktioniert noch nicht
			if (bronzePicsIndexList1.equals(bronzePicsOfUserIndexList1)) {
				
				List<UserPictureModel> kaufUserBronze = new ArrayList<UserPictureModel>();
				kaufUserBronze = userPictureRepo.findByUserAndPictureLevel(user, "bronze");
				
				int i = 1;
				
				for( UserPictureModel eintrag : kaufUserBronze)
				{
					if (eintrag.getAmount()>0) {
						
					} else {
						i++;
						rewardMessage = "Not enough pics";
					}
				}
				
				if(i==1) {
					for( UserPictureModel eintrag : kaufUserBronze)
					{
						eintrag.setAmount(eintrag.getAmount()-1);
						userPictureRepo.save(eintrag);
						
						rewardMessage = "Succesfully got bronze reward";
					}
				} else {
					rewardMessage = "Not enough pics";
				}
				
				System.out.print("GLEICH");
			} else {
				System.out.print("NICHT GLEICH");
				rewardMessage = "Not enough pics";
			}
			
			System.out.print("BRONZE von User: " + bronzePicsOfUserIndexList1.toString());
			System.out.print("BRONZE: " + bronzePicsIndexList1.toString());

			
			
			
			//generate the model for the frontend

		// all Pics of User
		bronzePicsOfUser = new ArrayList<UserPictureModel>();
		bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "bronze");
		List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
		silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "silber");
		List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
		goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user, "gold");

		// allPics
		List<PictureModel> bronzePics = new ArrayList<PictureModel>();
		bronzePics = pictureRepository.findByLevel("bronze");
		List<PictureModel> silverPics = new ArrayList<PictureModel>();
		silverPics = pictureRepository.findByLevel("silber");
		List<PictureModel> goldPics = new ArrayList<PictureModel>();
		goldPics = pictureRepository.findByLevel("gold");

		// generate all index List
		List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel bronzePicOfUser : bronzePicsOfUser) {
			bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
		}
		List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel silverPicOfUser : silverPicsOfUser) {
			silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
		}
		List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel goldPicOfUser : goldPicsOfUser) {
			goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
		}

		// missing Picture List
		List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();

		for (PictureModel bronzePic : bronzePics) {
			if (bronzePicsOfUserIndexList.contains(bronzePic.getPictureId())) {

			} else {
				missingPicsBronze.add(bronzePic);
			}

		}
		for (PictureModel goldPic : goldPics) {
			if (goldPicsOfUserIndexList.contains(goldPic.getPictureId())) {

			} else {
				missingPicsGold.add(goldPic);
			}

		}
		for (PictureModel silverPic : silverPics) {
			if (silverPicsOfUserIndexList.contains(silverPic.getPictureId())) {

			} else {
				missingPicsSilver.add(silverPic);
			}

		}

		model.addAttribute("bronzePicOfUser", bronzePicsOfUser);
		model.addAttribute("silverPicOfUser", silverPicsOfUser);
		model.addAttribute("goldPicOfUser", goldPicsOfUser);

		/*
		 * System.out.print(missingPicsBronze); System.out.print(missingPicsSilver);
		 * System.out.print(missingPicsGold);
		 */

		model.addAttribute("missingBronzePics", missingPicsBronze);
		model.addAttribute("missingSilverPics", missingPicsSilver);
		model.addAttribute("missingGoldPics", missingPicsGold);
		model.addAttribute("user", user);
		
		model.addAttribute("pack", rewardMessage);
		//sendMail(user, "REWARD-SILVER", rewardMessage);
		return "picture";

	}

	/**
	 * Display the upload form
	 * 
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showUploadForm(Model model, @RequestParam("id") int userId) {
		model.addAttribute("userId", userId);
		return "uploadFile";
	}

	/**
	 * Save uploaded file to the database (as 1:1 relationship to user)
	 * 
	 * @param model
	 * @param userId
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadDocument(Model model, @RequestParam("userId") int userId,
			@RequestParam("myFile") MultipartFile file) {

		try {

			Optional<UserModel> userOpt = userQueryRepository.findById(userId);
			if (!userOpt.isPresent())
				throw new IllegalArgumentException("No user with id " + userId);

			UserModel user = userOpt.get();

			// Already a document available -> delete it
			if (user.getDocument() != null) {
				documentRepository.delete(user.getDocument());
				// Don't forget to remove the relationship too
				user.setDocument(null);
			}

			// Create a new document and set all available infos

			DocumentModel document = new DocumentModel();
			document.setContent(file.getBytes());
			document.setContentType(file.getContentType());
			document.setCreated(new Date());
			document.setFilename(file.getOriginalFilename());
			document.setName(file.getName());
			user.setDocument(document);
			userQueryRepository.save(user);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error:" + e.getMessage());
		}

		// return "forward:/settings";
		return "redirect:/picture";
	}

	@RequestMapping("/download")
	public void download(@RequestParam("documentId") int documentId, HttpServletResponse response) {

		Optional<DocumentModel> docOpt = documentRepository.findById(documentId);
		if (!docOpt.isPresent())
			throw new IllegalArgumentException("No document with id " + documentId);

		DocumentModel doc = docOpt.get();

		try {
			response.setHeader("Content-Disposition", "inline;filename=\"" + doc.getFilename() + "\"");
			OutputStream out = response.getOutputStream();
			// application/octet-stream
			response.setContentType(doc.getContentType());
			out.write(doc.getContent());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Methode for sending Mail; leider noch nicht 
/*	private void sendMail(UserModel user, String rewardType, String message) {
	
		String content = "";
		// Create a thread safe "copy" of the template message and customize it
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		
		// You can override default settings from dispatcher-servlet.xml:
		 msg.setFrom("c969671571-e272d8@inbox.mailtrap.io");
		 msg.setTo("c969671571-e272d8@inbox.mailtrap.io"); //f�rs testen an die gleiche sonst user.getEmail()
		 msg.setSubject(rewardType);
		msg.setText(String.format(msg.getText(), "Max Mustermann", content));
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			ex.printStackTrace();
		}
	}*/
	
	
	/*
	 * @ExceptionHandler(Exception.class) public String handleAllException(Exception
	 * ex) { return "error"; }
	 */

}