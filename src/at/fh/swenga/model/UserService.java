package at.fh.swenga.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;




@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class UserService {
	
 private static List<UserModel> users = new ArrayList<UserModel>();
	
	
	public static   List<UserModel> getAllUsers()
	{
		return users;
		
	}

	public static   void addUser(UserModel user) {
		
		users.add(user);
	}

}
