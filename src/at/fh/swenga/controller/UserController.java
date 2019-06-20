package at.fh.swenga.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.validation.Valid;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import at.fh.swenga.model.PictureModel;
import at.fh.swenga.model.RoleModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserPictureModel;
import at.fh.swenga.model.UserService;
import at.fh.swenga.repository.ForumentryRepository;
import at.fh.swenga.repository.LogRepository;
import at.fh.swenga.repository.PictureRepository;
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
	RoleRepository roleRepository;
	@Autowired
	LogRepository logRepository;
	@Autowired
	PictureRepository pictureRepository;
	@Autowired
	UserPictureDao userPictureDao;
	@Autowired
	UserPictureRepo userPictureRepo;

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

		Date date = new Date();
		
		/*if (users.isEmpty()) {
			UserModel u1 = new UserModel("person", "test", "user", now, "w", 1.70, 70.5, 2, "test@schwinger", 100,
					false, true, "password");
			LogModel l1 = new LogModel(u1, u1.getHeight(), u1.getWeight(), u1.getPoints(), date);
			
			u1.encryptPassword();
			u1.addRoleModel(userRole);
			userRepository.persist(u1);
			logRepository.persist(l1);
			
			
			UserModel u2 = new UserModel("person", "test", "admin", now, "m", 1.80, 80.7, 2, "test@schwinge2r", 100,
					false, true, "password");
			LogModel l2 = new LogModel(u2, u2.getHeight(), u2.getWeight(), u2.getPoints(), date);
			
			u2.encryptPassword();
			u2.addRoleModel(userRole);
			u2.addRoleModel(coachRole);
			u2.addRoleModel(adminRole);
			userRepository.persist(u2);
			logRepository.persist(l2);
		}*/
		
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
	public String getPicture(Model model,Authentication authentication) {
		
		//get logged User
		String authenName = authentication.getName();
		UserModel user = userQueryRepository.findByUserName(authenName);
		
		//all Pics of User
		List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
		bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"bronze");
		List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
		silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"silber");
		List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
		goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"gold");
		
		//allPics
		List<PictureModel> bronzePics = new ArrayList<PictureModel>();
		bronzePics = pictureRepository.findByLevel("bronze");
		List<PictureModel> silverPics = new ArrayList<PictureModel>();
		silverPics = pictureRepository.findByLevel("silber");
		List<PictureModel> goldPics = new ArrayList<PictureModel>();
		goldPics = pictureRepository.findByLevel("gold");
		
		//generate all index List
		List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel bronzePicOfUser: bronzePicsOfUser)
		{
			bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
		}
		List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel silverPicOfUser: silverPicsOfUser)
		{
			silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
		}
		List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
		for (UserPictureModel goldPicOfUser: goldPicsOfUser)
		{
			goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
		}
		
		
		
		// missing Picture List
		List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
		List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();
		
		for(PictureModel bronzePic : bronzePics)
		{
			if(bronzePicsOfUserIndexList.contains(bronzePic.getPictureId()))
			{
				
			}
			else {
				missingPicsBronze.add(bronzePic);
			}
			
		}
		for(PictureModel goldPic : goldPics)
		{
			if(goldPicsOfUserIndexList.contains(goldPic.getPictureId()))
			{
				
			}
			else {
				missingPicsGold.add(goldPic);
			}
			
		}
		for(PictureModel silverPic : silverPics)
		{
			if(silverPicsOfUserIndexList.contains(silverPic.getPictureId()))
			{
				
			}
			else {
				missingPicsSilver.add(silverPic);
			}
			
		}
	
		
		
		model.addAttribute("bronzePicOfUser",bronzePicsOfUser);
		model.addAttribute("silverPicOfUser",silverPicsOfUser);
		model.addAttribute("goldPicOfUser",goldPicsOfUser);
		
		System.out.print(missingPicsBronze);
		System.out.print(missingPicsSilver);
		System.out.print(missingPicsGold);
		
		
		model.addAttribute("missingBronzePics",missingPicsBronze);
		model.addAttribute("missingSilverPics",missingPicsSilver);
		model.addAttribute("missingGoldPics",missingPicsGold);
		model.addAttribute("user",user);
		
		return "picture";
		
	}

	@RequestMapping(value = { "/forum" })
	public String getForum(Model model) {
		return "forum";
	}
	
	@RequestMapping(value = { "/registration" })
	public String getRegistration(Model model) {
		return "registration";
	}


	@RequestMapping(value = { "/profile" })
	public String getProfile(Model model, Authentication authentication) {

		UserModel user = null;

		String searchString = authentication.getName();

		user = userQueryRepository.findByUserName(searchString);
		Set<LogModel> logs = user.getLogs();
		
		
		//Liste mit Einträge für die Grafik für Javascript
		List<Double> heigtnumbers= new ArrayList<Double>();
		//int length =0;
		for( LogModel log : logs)
		{
			heigtnumbers.add(log.getHeight());
		}
		
		List<Double> weightnumbers= new ArrayList<Double>();
		for( LogModel log : logs)
		{
			weightnumbers.add(log.getWeight());
		}
		
		
		List<Double> bminumbers = new ArrayList<Double>();
		for (LogModel log : logs) {
			 double weight = log.getWeight();
			 double height = log.getHeight();
			 
			 double height2 = height*height;
			 double bmi = weight/height2;//weight/height*height;
			 
			 bminumbers.add(bmi);
		}
		
		List<Integer> pointnumbers= new ArrayList<Integer>();
		for( LogModel log : logs)
		{
			pointnumbers.add(log.getPoints());
		}
		
		List<Double> heights7 = heigtnumbers.subList(Math.max(heigtnumbers.size() - 7, 0), heigtnumbers.size());
		List<Double> weights7 = weightnumbers.subList(Math.max(weightnumbers.size() - 7, 0), weightnumbers.size());
		List<Double> bmi7 = bminumbers.subList(Math.max(bminumbers.size() - 7, 0), bminumbers.size());
		List<Integer> points7 = pointnumbers.subList(Math.max(pointnumbers.size() - 7, 0), pointnumbers.size());
		
		
		model.addAttribute("user", user);
		model.addAttribute("logs",heigtnumbers.toString()); // keyword für die Liste in /scripts/app/app-blog-overiew.1.1.0.js
		model.addAttribute("weights", weightnumbers.toString());
		model.addAttribute("bmis", bminumbers.toString());
		model.addAttribute("heights7", heights7.toString());
		model.addAttribute("weights7", weights7.toString());
		model.addAttribute("bmi7", bmi7.toString());
		model.addAttribute("points7", points7.toString());
		
		/*double heightvorl = heigtnumbers.get(heigtnumbers.size()-2);
		double heightletzte = heigtnumbers.get(heigtnumbers.size()-1);
		
		double heightproz = (heightvorl - heightletzte);
		
		model.addAttribute("heightproz", Double.toString(heightproz));*/
		
		
		
		
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
	
	@RequestMapping(value = "/fillPics")
	public String fillPics(Model model)
	{
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
	
	//for the gold pac 
	@RequestMapping(value = "/goldPack")
	public String goldPack(Model model, Authentication authentication) {
		
		//getting User
		String searchString = authentication.getName();
		UserModel user = userQueryRepository.findByUserName(searchString);
		
		//getting all pics of one level
		List<PictureModel> goldPics = new ArrayList<PictureModel>();
		goldPics = pictureRepository.findByLevel("gold");
		List<PictureModel> silverPics = new ArrayList<PictureModel>();
		silverPics = pictureRepository.findByLevel("silber");
		List<PictureModel> bronzePics = new ArrayList<PictureModel>();
		bronzePics = pictureRepository.findByLevel("bronze");
		
		//get the size for the id
		List<UserPictureModel> allModels = userPictureRepo.findAll();
		
		
		//get random numbers
		Random randGold = new Random();
		int randomGoldPic=randGold.nextInt(goldPics.size());
		
		Random randSilver = new Random();
		int randomSilverPic=randSilver.nextInt(silverPics.size());
		
		Random randBronze1 = new Random();
		int randomBronzePic1=randBronze1.nextInt(bronzePics.size());
		
		Random randBronze2 = new Random();
		int randomBronzePic2=randBronze2.nextInt(bronzePics.size());
		
		
		
		//get Pac
		ArrayList<PictureModel> picPack = new ArrayList<PictureModel>();
		picPack.add(goldPics.get(randomGoldPic));
		picPack.add(silverPics.get(randomSilverPic));
		picPack.add(bronzePics.get(randomBronzePic1));
		picPack.add(bronzePics.get(randomBronzePic2));
		
		
		//all Pictures from the logged user
				//List<UserPictureModel> ownedPictures = new ArrayList<UserPictureModel>();
				List<UserPictureModel> ownedPictures =  userPictureRepo.findByUser(user);
				

				//List of ownedPictureIds
				List<Integer> ownedPictureIndexList = new ArrayList<Integer>();
				for(UserPictureModel ownedPicture : ownedPictures)
				{
					ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
				}
		

		
		//compare ownedPics with new pics of Pac
		for(PictureModel pic : picPack)
		{
			
			//get the size for the id
			allModels = userPictureRepo.findAll();
			//if fürs update
			if (ownedPictureIndexList.contains(pic.getPictureId()))
					{
						UserPictureModel updateModel = userPictureRepo.findByUserAndPicture(user,pic);
						updateModel.setAmount(updateModel.getAmount()+1);
						userPictureDao.merge(updateModel);
						System.out.print("in update");
					}
		else
			{
			UserPictureModel newPicForUser= new UserPictureModel();
			newPicForUser.setId(allModels.size() +1);
			newPicForUser.setUser(user);
			newPicForUser.setPicture(pic);
			newPicForUser.setAmount(1);
			userPictureRepo.save(newPicForUser);
			
			System.out.print("in save");
			
		}
			
			ownedPictures =  userPictureRepo.findByUser(user);
			//List of ownedPictureIds update
			 ownedPictureIndexList = new ArrayList<Integer>();
			for(UserPictureModel ownedPicture : ownedPictures)
			{
				ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
			}
	}
		
		
		//generate the model for the frontend
		
		
		//get logged User
			
				
				//all Pics of User
				List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
				bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"bronze");
				List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
				silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"silber");
				List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
				goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"gold");
				
				//allPics
				bronzePics = new ArrayList<PictureModel>();
				bronzePics = pictureRepository.findByLevel("bronze");
				silverPics = new ArrayList<PictureModel>();
				silverPics = pictureRepository.findByLevel("silber");
				goldPics = new ArrayList<PictureModel>();
				goldPics = pictureRepository.findByLevel("gold");
				
				//generate all index List
				List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel bronzePicOfUser: bronzePicsOfUser)
				{
					bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
				}
				List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel silverPicOfUser: silverPicsOfUser)
				{
					silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
				}
				List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel goldPicOfUser: goldPicsOfUser)
				{
					goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
				}
				
				// missing Picture List
				List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
				List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
				List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();
				
				for(PictureModel bronzePic : bronzePics)
				{
					if(bronzePicsOfUserIndexList.contains(bronzePic.getPictureId()))
					{
						
					}
					else {
						missingPicsBronze.add(bronzePic);
					}
					
				}
				for(PictureModel goldPic : goldPics)
				{
					if(goldPicsOfUserIndexList.contains(goldPic.getPictureId()))
					{
						
					}
					else {
						missingPicsGold.add(goldPic);
					}
					
				}
				for(PictureModel silverPic : silverPics)
				{
					if(silverPicsOfUserIndexList.contains(silverPic.getPictureId()))
					{
						
					}
					else {
						missingPicsSilver.add(silverPic);
					}
					
				}
			
				
				
				model.addAttribute("bronzePicOfUser",bronzePicsOfUser);
				model.addAttribute("silverPicOfUser",silverPicsOfUser);
				model.addAttribute("goldPicOfUser",goldPicsOfUser);
				
				System.out.print(missingPicsBronze);
				System.out.print(missingPicsSilver);
				System.out.print(missingPicsGold);
				
				
				model.addAttribute("missingBronzePics",missingPicsBronze);
				model.addAttribute("missingSilverPics",missingPicsSilver);
				model.addAttribute("missingGoldPics",missingPicsGold);
				model.addAttribute("user",user);
				
		return "picture";
		
	}
	
	//for the silver pac
	@RequestMapping(value = "/silverPack")
	public String silverPack(Model model, Authentication authentication) {
			
			//getting User
			String searchString = authentication.getName();
			UserModel user = userQueryRepository.findByUserName(searchString);
			
			//getting all pics of one level
			List<PictureModel> silverPics = new ArrayList<PictureModel>();
			silverPics = pictureRepository.findByLevel("silber");
			List<PictureModel> bronzePics = new ArrayList<PictureModel>();
			bronzePics = pictureRepository.findByLevel("bronze");
			
			//get the size for the id
			List<UserPictureModel> allModels = userPictureRepo.findAll();
			
			
			//get random numbers
			Random randSilver = new Random();
			int randomSilverPic=randSilver.nextInt(silverPics.size());
			
			Random randBronze1 = new Random();
			int randomBronzePic1=randBronze1.nextInt(bronzePics.size());
			
			Random randBronze2 = new Random();
			int randomBronzePic2=randBronze2.nextInt(bronzePics.size());
			
			
			
			//get Pac
			ArrayList<PictureModel> picPack = new ArrayList<PictureModel>();
			picPack.add(silverPics.get(randomSilverPic));
			picPack.add(bronzePics.get(randomBronzePic1));
			picPack.add(bronzePics.get(randomBronzePic2));
			
			
			
			//all Pictures from the logged user
			//List<UserPictureModel> ownedPictures = new ArrayList<UserPictureModel>();
			List<UserPictureModel> ownedPictures =  userPictureRepo.findByUser(user);
			

			//List of ownedPictureIds
			List<Integer> ownedPictureIndexList = new ArrayList<Integer>();
			for(UserPictureModel ownedPicture : ownedPictures)
			{
				ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
			}

			
			//compare ownedPics with new pics of Pac
			for(PictureModel pic : picPack)
			{
				
				//get the size for the id
				allModels = userPictureRepo.findAll();
				//if fürs update
				if (ownedPictureIndexList.contains(pic.getPictureId()))
						{
							UserPictureModel updateModel = userPictureRepo.findByUserAndPicture(user,pic);
							updateModel.setAmount(updateModel.getAmount()+1);
							userPictureDao.merge(updateModel);
							System.out.print("in update");
						}
			else
				{
				UserPictureModel newPicForUser= new UserPictureModel();
				newPicForUser.setId(allModels.size() +1);
				newPicForUser.setUser(user);
				newPicForUser.setPicture(pic);
				newPicForUser.setAmount(1);
				userPictureRepo.save(newPicForUser);
				
				System.out.print("in save");
				
			}
				ownedPictures =  userPictureRepo.findByUser(user);
				//List of ownedPictureIds update
				 ownedPictureIndexList = new ArrayList<Integer>();
				for(UserPictureModel ownedPicture : ownedPictures)
				{
					ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
				}
		}
			
			//generate the model for the frontend
			
			
			//get logged User
				
					
					//all Pics of User
					List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
					bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"bronze");
					List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
					silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"silber");
					List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
					goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"gold");
					
					//allPics
					bronzePics = new ArrayList<PictureModel>();
					bronzePics = pictureRepository.findByLevel("bronze");
					silverPics = new ArrayList<PictureModel>();
					silverPics = pictureRepository.findByLevel("silber");
					List<PictureModel> goldPics = new ArrayList<PictureModel>();
					goldPics = pictureRepository.findByLevel("gold");
					
					//generate all index List
					List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
					for (UserPictureModel bronzePicOfUser: bronzePicsOfUser)
					{
						bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
					}
					List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
					for (UserPictureModel silverPicOfUser: silverPicsOfUser)
					{
						silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
					}
					List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
					for (UserPictureModel goldPicOfUser: goldPicsOfUser)
					{
						goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
					}
					
					// missing Picture List
					List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
					List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
					List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();
					
					for(PictureModel bronzePic : bronzePics)
					{
						if(bronzePicsOfUserIndexList.contains(bronzePic.getPictureId()))
						{
							
						}
						else {
							missingPicsBronze.add(bronzePic);
						}
						
					}
					for(PictureModel goldPic : goldPics)
					{
						if(goldPicsOfUserIndexList.contains(goldPic.getPictureId()))
						{
							
						}
						else {
							missingPicsGold.add(goldPic);
						}
						
					}
					for(PictureModel silverPic : silverPics)
					{
						if(silverPicsOfUserIndexList.contains(silverPic.getPictureId()))
						{
							
						}
						else {
							missingPicsSilver.add(silverPic);
						}
						
					}
				
					
					
					model.addAttribute("bronzePicOfUser",bronzePicsOfUser);
					model.addAttribute("silverPicOfUser",silverPicsOfUser);
					model.addAttribute("goldPicOfUser",goldPicsOfUser);
					
					System.out.print(missingPicsBronze);
					System.out.print(missingPicsSilver);
					System.out.print(missingPicsGold);
					
					
					model.addAttribute("missingBronzePics",missingPicsBronze);
					model.addAttribute("missingSilverPics",missingPicsSilver);
					model.addAttribute("missingGoldPics",missingPicsGold);
					model.addAttribute("user",user);
					
			
			
			
			
			
			return "picture";
			
		}
		
		//for the bronze pac
	@RequestMapping(value = "/bronzePack")
	public String bronzePack(Model model, Authentication authentication) {
			
			//getting User
			String searchString = authentication.getName();
			UserModel user = userQueryRepository.findByUserName(searchString);
			
			//getting all pics of one level
			List<PictureModel> bronzePics = new ArrayList<PictureModel>();
			bronzePics = pictureRepository.findByLevel("bronze");
			
			//get the size for the id
			List<UserPictureModel> allModels = userPictureRepo.findAll();
			
			
			//get random numbers
			Random randBronze1 = new Random();
			int randomBronzePic1=randBronze1.nextInt(bronzePics.size());
			
			Random randBronze2 = new Random();
			int randomBronzePic2=randBronze2.nextInt(bronzePics.size());
			
			
			
			//get Pac
			ArrayList<PictureModel> picPack = new ArrayList<PictureModel>();
			picPack.add(bronzePics.get(randomBronzePic1));
			picPack.add(bronzePics.get(randomBronzePic2));
			
			
			
			//all Pictures from the logged user
			//List<UserPictureModel> ownedPictures = new ArrayList<UserPictureModel>();
			List<UserPictureModel> ownedPictures =  userPictureRepo.findByUser(user);
			

			//List of ownedPictureIds
			List<Integer> ownedPictureIndexList = new ArrayList<Integer>();
			for(UserPictureModel ownedPicture : ownedPictures)
			{
				ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
			}

			
			//compare ownedPics with new pics of Pac
			for(PictureModel pic : picPack)
			{
				
				//get the size for the id
				allModels = userPictureRepo.findAll();
				//if fürs update
				if (ownedPictureIndexList.contains(pic.getPictureId()))
						{
							UserPictureModel updateModel = userPictureRepo.findByUserAndPicture(user,pic);
							updateModel.setAmount(updateModel.getAmount()+1);
							userPictureDao.merge(updateModel);
							System.out.print("in update");
						}
			else
				{
				UserPictureModel newPicForUser= new UserPictureModel();
				newPicForUser.setId(allModels.size() +1);
				newPicForUser.setUser(user);
				newPicForUser.setPicture(pic);
				newPicForUser.setAmount(1);
				userPictureRepo.save(newPicForUser);
				
				System.out.print("in save");
				
			}
				ownedPictures =  userPictureRepo.findByUser(user);
				//List of ownedPictureIds update
				 ownedPictureIndexList = new ArrayList<Integer>();
				for(UserPictureModel ownedPicture : ownedPictures)
				{
					ownedPictureIndexList.add(ownedPicture.getPicture().getPictureId());
				}
		}
			
			
			//generate the model for the frontend
			
			
			//get logged User
				
					
					//all Pics of User
					List<UserPictureModel> bronzePicsOfUser = new ArrayList<UserPictureModel>();
					bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"bronze");
					List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
					silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"silber");
					List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
					goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"gold");
					
					//allPics
					bronzePics = new ArrayList<PictureModel>();
					bronzePics = pictureRepository.findByLevel("bronze");
					List<PictureModel> silverPics = new ArrayList<PictureModel>();
					silverPics = pictureRepository.findByLevel("silber");
					List<PictureModel> goldPics = new ArrayList<PictureModel>();
					goldPics = pictureRepository.findByLevel("gold");
					
					//generate all index List
					List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
					for (UserPictureModel bronzePicOfUser: bronzePicsOfUser)
					{
						bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
					}
					List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
					for (UserPictureModel silverPicOfUser: silverPicsOfUser)
					{
						silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
					}
					List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
					for (UserPictureModel goldPicOfUser: goldPicsOfUser)
					{
						goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
					}
					
					// missing Picture List
					List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
					List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
					List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();
					
					for(PictureModel bronzePic : bronzePics)
					{
						if(bronzePicsOfUserIndexList.contains(bronzePic.getPictureId()))
						{
							
						}
						else {
							missingPicsBronze.add(bronzePic);
						}
						
					}
					for(PictureModel goldPic : goldPics)
					{
						if(goldPicsOfUserIndexList.contains(goldPic.getPictureId()))
						{
							
						}
						else {
							missingPicsGold.add(goldPic);
						}
						
					}
					for(PictureModel silverPic : silverPics)
					{
						if(silverPicsOfUserIndexList.contains(silverPic.getPictureId()))
						{
							
						}
						else {
							missingPicsSilver.add(silverPic);
						}
						
					}
					model.addAttribute("bronzePicOfUser",bronzePicsOfUser);
					model.addAttribute("silverPicOfUser",silverPicsOfUser);
					model.addAttribute("goldPicOfUser",goldPicsOfUser);
					
					System.out.print(missingPicsBronze);
					System.out.print(missingPicsSilver);
					System.out.print(missingPicsGold);
					
					
					model.addAttribute("missingBronzePics",missingPicsBronze);
					model.addAttribute("missingSilverPics",missingPicsSilver);
					model.addAttribute("missingGoldPics",missingPicsGold);
					model.addAttribute("user",user);
					
			return "picture";
			
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
		
		
		
		
		
		Date date = new Date();
		
		
		//Hier werden die Änderungen eines Users in den Log eingetragen
		LogModel log = new LogModel(user, user.getHeight(), user.getWeight(), user.getPoints(), date);
		System.out.println(log);
		//logRepository.save(log); 
		//log.setUser(user);
		//user.addLogModel(log);
		logRepository.save(log);
		
		
		//unser Ansatz um das Problem zu l�sen. Jedoch funktioniert die zuweisung zu user nicht.
		
		 
		
		// coach fehlt noch

		// Save a message for the web page

		model.addAttribute("message", "update succes by " + changedUserModel.toString());
		//model.addAttribute("user", user);
		return "forward:/profile";
	}

	//bronze Reward
	@RequestMapping(value = {"/bronzeReward"})
	public String bronzeReward(Model model, Authentication authentication) {
		
		String authenname = authentication.getName();
		UserModel user = userQueryRepository.findByUserName(authenname);
		
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
		
		if (bronzePicsIndexList1.equals(bronzePicsOfUserIndexList1)) {
			
			/*List<UserPictureModel> kaufUserBronze = new ArrayList<UserPictureModel>();
			kaufUserBronze = userPictureRepo.findByUser(user);
			
			List<Integer> amounts= new ArrayList<Integer>();
			
			
			for(UserPictureModel eintrag:
			{
				
			}*/
				
			
			
			
			System.out.print("GLEICH");
		} else {
			System.out.print("NICHT GLEICH");
		}
		
		System.out.print("BRONZE von User: " + bronzePicsOfUserIndexList1.toString());
		System.out.print("BRONZE: " + bronzePicsIndexList1.toString());

		
		
		
		//generate the model for the frontend
		
				//all Pics of User
				bronzePicsOfUser = new ArrayList<UserPictureModel>();
				bronzePicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"bronze");
				List<UserPictureModel> silverPicsOfUser = new ArrayList<UserPictureModel>();
				silverPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"silber");
				List<UserPictureModel> goldPicsOfUser = new ArrayList<UserPictureModel>();
				goldPicsOfUser = userPictureRepo.findByUserAndPictureLevel(user,"gold");
				
				//allPics
				List<PictureModel>bronzePics = new ArrayList<PictureModel>();
				bronzePics = pictureRepository.findByLevel("bronze");
				List<PictureModel> silverPics = new ArrayList<PictureModel>();
				silverPics = pictureRepository.findByLevel("silber");
				List<PictureModel> goldPics = new ArrayList<PictureModel>();
				goldPics = pictureRepository.findByLevel("gold");
				
				//generate all index List
				List<Integer> bronzePicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel bronzePicOfUser: bronzePicsOfUser)
				{
					bronzePicsOfUserIndexList.add(bronzePicOfUser.getPicture().getPictureId());
				}
				List<Integer> silverPicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel silverPicOfUser: silverPicsOfUser)
				{
					silverPicsOfUserIndexList.add(silverPicOfUser.getPicture().getPictureId());
				}
				List<Integer> goldPicsOfUserIndexList = new ArrayList<Integer>();
				for (UserPictureModel goldPicOfUser: goldPicsOfUser)
				{
					goldPicsOfUserIndexList.add(goldPicOfUser.getPicture().getPictureId());
				}
				
				// missing Picture List
				List<PictureModel> missingPicsBronze = new ArrayList<PictureModel>();
				List<PictureModel> missingPicsSilver = new ArrayList<PictureModel>();
				List<PictureModel> missingPicsGold = new ArrayList<PictureModel>();
				
				for(PictureModel bronzePic : bronzePics)
				{
					if(bronzePicsOfUserIndexList.contains(bronzePic.getPictureId()))
					{
						
					}
					else {
						missingPicsBronze.add(bronzePic);
					}
					
				}
				for(PictureModel goldPic : goldPics)
				{
					if(goldPicsOfUserIndexList.contains(goldPic.getPictureId()))
					{
						
					}
					else {
						missingPicsGold.add(goldPic);
					}
					
				}
				for(PictureModel silverPic : silverPics)
				{
					if(silverPicsOfUserIndexList.contains(silverPic.getPictureId()))
					{
						
					}
					else {
						missingPicsSilver.add(silverPic);
					}
					
				}
			
				
				
				model.addAttribute("bronzePicOfUser",bronzePicsOfUser);
				model.addAttribute("silverPicOfUser",silverPicsOfUser);
				model.addAttribute("goldPicOfUser",goldPicsOfUser);
				
				/*System.out.print(missingPicsBronze);
				System.out.print(missingPicsSilver);
				System.out.print(missingPicsGold);*/
				
				
				model.addAttribute("missingBronzePics",missingPicsBronze);
				model.addAttribute("missingSilverPics",missingPicsSilver);
				model.addAttribute("missingGoldPics",missingPicsGold);
				model.addAttribute("user",user);
				
		
		
		
		return "picture";
		
		
	}
	
	
	
	/*@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}*/

	
}