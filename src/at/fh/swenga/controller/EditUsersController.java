package at.fh.swenga.controller;

import at.fh.swenga.model.UserModel;
import at.fh.swenga.repository.*;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditUsersController
{
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

    @RequestMapping("/editUsers")
    public String editUsers(Model model, Authentication authentication)
    {
        List<UserModel> users = userQueryRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", authentication.getName());
        return "editUsers";
    }

    @RequestMapping("/editSingleUser")
    public String editSingleUser(Model model, int userId)
    {
        Optional<UserModel> user = userQueryRepository.findById(userId);
        model.addAttribute("user", user.get());
        findCoaches(model);
        return "editSingleUser";
    }

    @RequestMapping("/safeSingleUser")
    public String safeSingleUser(Model model, UserModel changedUserModel,
    		BindingResult bindingResult,@RequestParam int userId)
    {
        if(bindingResult.hasErrors())
        {
            String errorMessage = "";
            for(Iterator iterator = bindingResult.getFieldErrors().iterator(); iterator.hasNext();)
            {
                FieldError fieldError = (FieldError)iterator.next();
                errorMessage = (new StringBuilder(String.valueOf(errorMessage))).append(fieldError.getField()).append(" is invalid: ").append(fieldError.getCode()).append("<br>").toString();
            }

            model.addAttribute("errorMessage", errorMessage);
            System.out.print(changedUserModel);
            model.addAttribute("user", changedUserModel);
            findCoaches(model);
            return "registration";
        }

        if(changedUserModel.getPassword().length() >= 6)
        {
            Optional user1 = userQueryRepository.findById(Integer.valueOf(userId));
            UserModel user = (UserModel)user1.get();
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
            if(changedUserModel.getPassword().length() != 60)
                user.encryptPassword();
            user = userRepository.merge(user);
            System.out.print("MEEEEEEEEEEEERGE");
            model.addAttribute("message", (new StringBuilder("User ")).append(changedUserModel.getFirstName()).append(" ").append(changedUserModel.getLastName()).append(" updated successfully!").toString());
            return "forward:/editUsers";
        } else
        {
            model.addAttribute("errorMessage", "Password is not the same or it has to be at least six characters long!");
            model.addAttribute("user", changedUserModel);
            findCoaches(model);
            return "editSingleUser";
        }
    }

    @RequestMapping("/deleteSingleUser")
    public String deleteSingleUser(Model model,@RequestParam int userId)
    {
        Optional<UserModel> user = userQueryRepository.findById(userId);
        model.addAttribute("message", (new StringBuilder("User ")).append(((UserModel)user.get()).getFirstName()).append(" ").append(((UserModel)user.get()).getLastName()).append(" successfully deleted!").toString());
        userQueryRepository.deleteById(Integer.valueOf(userId));
        return "forward:/editUsers";
    }


}
