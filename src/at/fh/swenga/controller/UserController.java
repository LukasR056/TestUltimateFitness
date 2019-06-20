package at.fh.swenga.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import at.fh.swenga.model.ExerciseModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserService;
import at.fh.swenga.repository.ExerciseRepository;
import at.fh.swenga.repository.ForumentryRepository;
//import at.fh.swenga.repository.LogRepository;
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

	/*
	 * @Autowired LogRepository logRepository;
	 * 
	 * @Autowired ForumentryRepository forumentryRepository;
	 */

	@RequestMapping(value = { "/" })
	public String index(Model model, String name, String type) {

		// test users erstellen und gespeichert.

		Date now = new Date();

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

		return "index";
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
		}
		return "profile";

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

		// coach fehlt noch

		// Save a message for the web page

		model.addAttribute("message", "update succes by " + changedUserModel.toString());
		// model.addAttribute("user", user);
		return "forward:/profile";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}
}