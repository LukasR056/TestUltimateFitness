package at.fh.swenga.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.model.ExerciseModel;
import at.fh.swenga.model.MessageModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.repository.MessageRepository;
import at.fh.swenga.repository.UserQueryRepository;

@Controller
public class MessageController {

	@Autowired
	MessageRepository messageRepository;
	@Autowired
	UserQueryRepository userQueryRepository;

	// get all Messages
	@RequestMapping("/messages")
	public String showMessages(Model model, Authentication authentication) {
		UserModel user = userQueryRepository.findByUserName(authentication.getName());

		List<MessageModel> allUsersMessages = new ArrayList<MessageModel>();
		allUsersMessages = messageRepository.findByToUser(user);
		model.addAttribute("user", user);
		model.addAttribute("messages", allUsersMessages);
		return "messages";
	}

	// write Message
	@RequestMapping("/newMessage")
	public String newMessages(Model model, Authentication authentication) {
		UserModel user = userQueryRepository.findByUserName(authentication.getName());

		model.addAttribute("user", user);
		return "newMessage";
	}

	// sending Messages
	@RequestMapping("/sendMessage")
	public String sendMessages(Model model, Authentication authentication, 
			@RequestParam String userName, @RequestParam String subject, @RequestParam String message ) {
		
		
		UserModel toUser = userQueryRepository.findByUserName(userName);
		UserModel fromUser = userQueryRepository.findByUserName(authentication.getName());
		MessageModel newMessage = new MessageModel(subject,fromUser,toUser,message);
		
		
		System.out.print(newMessage.toString()+ "aaaaaaaaaaaa");
		
		
		List<UserModel> users = userQueryRepository.findAll();
		if (users.contains(newMessage.getToUser())) {
			newMessage.setFromUser(fromUser);
			messageRepository.save(newMessage);
		}  else {
			model.addAttribute("errorMessage","No User with such a name!");
			return "forward:/newMessage";
			
		}
		model.addAttribute("user", fromUser);
		return "forward:/messages";
	}

	// deleteMessage
	@RequestMapping(value = { "deleteMessage" })
	public String deleteExercise(Model model, @RequestParam int id, Authentication authentication) {
		// Get User
		MessageModel deleteMessage = messageRepository.findById(id);

		messageRepository.delete(deleteMessage);

		UserModel user = userQueryRepository.findByUserName(authentication.getName());

		List<MessageModel> allUsersMessages = new ArrayList<MessageModel>();
		allUsersMessages = messageRepository.findByToUser(user);
		model.addAttribute("user", user);
		model.addAttribute("messages", allUsersMessages);

		return "messages";
	}

}
