package at.fh.swenga.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.model.UserModel;
import at.fh.swenga.repository.RoleQueryRepository;
import at.fh.swenga.repository.RoleRepository;
import at.fh.swenga.repository.UserQueryRepository;
import at.fh.swenga.repository.UserRepository;

@Controller
public class EditUsersController {
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

	@Secured("ROLE_ADMIN")
	@RequestMapping("/editUsers")
	public String editUsers(Model model, Authentication authentication) {
		List<UserModel> users = userQueryRepository.findAll();
		model.addAttribute("users", users);
		model.addAttribute("user", authentication.getName());
		return "editUsers";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/editSingleUser")
	public String editSingleUser(Model model, int userId) {
		Optional<UserModel> user = userQueryRepository.findById(userId);
		model.addAttribute("user", user.get());
		findCoaches(model);
		return "editSingleUser";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/safeSingleUser")
	public String safeSingleUser(Model model, UserModel changedUserModel, BindingResult bindingResult,
			@RequestParam int userId) {
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}

			model.addAttribute("errorMessage", errorMessage);
			System.out.print(changedUserModel);
			model.addAttribute("user", changedUserModel);
			findCoaches(model);
			return "safeSingleUser";
		}

		if (changedUserModel.getPassword().length() >= 6) {
			
			Optional user1 = userQueryRepository.findById(Integer.valueOf(userId));
			UserModel user = (UserModel) user1.get();
			
			System.out.print((new StringBuilder("COOOL")).append(user).toString());
			user.setFirstName(changedUserModel.getFirstName());
			user.setLastName(changedUserModel.getLastName());
			user.setUserName(changedUserModel.getUserName());
			user.setBirthDate(changedUserModel.getBirthDate());
			user.setGender(changedUserModel.getGender());
			user.setHeight(changedUserModel.getHeight());
			user.setWeight(changedUserModel.getWeight());
			user.setCoach(changedUserModel.getCoach());
			user.seteMail(changedUserModel.geteMail());
			user.setPoints(changedUserModel.getPoints());
			user.setEnabled(changedUserModel.isEnabled());
			user.setPassword(changedUserModel.getPassword());
			System.out.print(user);
			if (changedUserModel.getPassword().length() != 60)
				user.encryptPassword();

			// Überprüfen ob User Coach ist
			if (user.getCoach() == "") {
				user.addRoleModel(roleRepository.getRole("ROLE_COACH"));
				System.out.print("HINZUGABE ROLE COACH");
			} else if (user.getCoach() != "") {
				user.removeRoleModel(roleRepository.getRole("ROLE_COACH"));
				System.out.print("ENTFERNUNG ROLE COACH");
			}

			user = userRepository.merge(user);
			System.out.print("MEEEEEEEEEEEERGE");
			model.addAttribute("message", (new StringBuilder("User ")).append(changedUserModel.getFirstName())
					.append(" ").append(changedUserModel.getLastName()).append(" updated successfully!").toString());
			return "forward:/editUsers";
		} else {
			model.addAttribute("errorMessage",
					"Password is not the same or it has to be at least six characters long!");
			model.addAttribute("user", changedUserModel);
			findCoaches(model);
			return "editSingleUser";
		}
	}

	@RequestMapping("/deleteSingleUser")
	public String deleteSingleUser(Model model, @RequestParam int userId) {
		Optional<UserModel> user = userQueryRepository.findById(userId);
		model.addAttribute("message",
				(new StringBuilder("User ")).append(((UserModel) user.get()).getFirstName()).append(" ")
						.append(((UserModel) user.get()).getLastName()).append(" successfully deleted!").toString());
		userQueryRepository.deleteById(Integer.valueOf(userId));
		return "forward:/editUsers";
	}

}
