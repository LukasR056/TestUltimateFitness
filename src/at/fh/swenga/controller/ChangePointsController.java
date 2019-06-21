package at.fh.swenga.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.model.ExerciseModel;
import at.fh.swenga.model.LogModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.repository.RoleQueryRepository;
import at.fh.swenga.repository.RoleRepository;
import at.fh.swenga.repository.UserQueryRepository;
import at.fh.swenga.repository.UserRepository;

@Controller
public class ChangePointsController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserQueryRepository userQueryRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleQueryRepository roleQueryRepository;

	@InitBinder
	public void initDateBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	public void findCoaches(Model model) {
		List<UserModel> coaches = null;
		coaches = userQueryRepository.findCoach();
		model.addAttribute("coaches", coaches);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COACH"})
	@RequestMapping("/changeUserPoints")
	public String changeUserPoints(Model model, Authentication authentication) {
		String coach = authentication.getName();
		List<UserModel> trainees = userQueryRepository.findByCoach(coach);

		model.addAttribute("trainees", trainees);
		model.addAttribute("coach", coach);
		return "changeUserPoints";
	}

	@Secured({"ROLE_ADMIN", "ROLE_COACH"})
	@RequestMapping("/changeSingleUserPoints")
	public String editSingleUser(Model model, @RequestParam int userId) {
		Optional<UserModel> user1 = userQueryRepository.findById(userId);
		UserModel user = user1.get();
		model.addAttribute("user", user);

		// Liste mit Einträge für die Grafik für Javascript
		Set<LogModel> logs = user.getLogs();
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

		// piediagram
		List<ExerciseModel> exercisesFromUser = user.getExercises();

		int stomachCounter = 0;
		int legCounter = 0;
		int shoulderCounter = 0;
		int chestCounter = 0;

		for (ExerciseModel exercise : exercisesFromUser) {

			if (exercise.getType().equals("Stomach")) {
				stomachCounter = stomachCounter + 1;
			}
			if (exercise.getType().equals("Legs")) {
				legCounter = legCounter + 1;
			}
			if (exercise.getType().equals("Shoulders")) {
				shoulderCounter = shoulderCounter + 1;
			}
			if (exercise.getType().equals("Chest")) {
				chestCounter = chestCounter + 1;
			}

		}

		List<Integer> counterList = new ArrayList<Integer>();
		counterList.add(stomachCounter);
		counterList.add(shoulderCounter);
		counterList.add(legCounter);
		counterList.add(chestCounter);

		model.addAttribute("user", user);
		model.addAttribute("logs", heigtnumbers.toString()); // keyword fÃ¼r die Liste in
		model.addAttribute("counterList", counterList); // /scripts/app/app-blog-overiew.1.1.0.js
		model.addAttribute("weights", weightnumbers.toString());
		model.addAttribute("bmis", bminumbers.toString());

		return "changeSingleUserPoints";
	}

	@Secured({"ROLE_ADMIN", "ROLE_COACH"})
	@RequestMapping("/safeSingleUserPoints")
	public String safeSingleUser(Model model, @Valid UserModel changedUserModel, BindingResult bindingResult,
			@RequestParam int userId) {

		Optional<UserModel> user1 = userQueryRepository.findById(userId);
		UserModel user = (UserModel) user1.get();

		user.setPoints(changedUserModel.getPoints());

		System.out.print(user);

		user = userRepository.merge(user);
		model.addAttribute("message", "Points of User " + changedUserModel.getFirstName() + " "
				+ changedUserModel.getLastName() + " were successfully changed!");
		// model.addAttribute("message", (new StringBuilder("User
		// ")).append(changedUserModel.getFirstName()).append("
		// ").append(changedUserModel.getLastName()).append(" updated
		// successfully!").toString());
		return "forward:/changeUserPoints";

	}

}
