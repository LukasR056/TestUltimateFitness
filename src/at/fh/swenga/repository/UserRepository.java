package at.fh.swenga.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	
	List<UserModel> users = new ArrayList<UserModel>();
	
	
	public static  List<UserModel> getAllUsers()
	{
		return users;
		
	}

	public static void addUser(UserModel user) {
		
		users.add(user);
	}
	
	
	
	

}
