package at.fh.swenga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.UserModel;

@Repository
@Transactional
public interface UserQueryRepository extends JpaRepository<UserModel, Integer> {

	UserModel findByUserName(String searchString);

	UserModel getUserByUserName(String searchString);
	
	
/*	@Query("Delete ue FROM User u INNER JOIN User_Exercise ue on  u.id = ue.User_id INNER JOIN Exercise e on    ue.Exercise_id = e.id WHERE u.userName =:userName AND e.name = ':exerciseName'")
	List<UserModel> deleteExerciseFromUser(@Param("userName") String userName,@Param("exerciseName") String exerciseName); */



	

	
 
	



	
}
