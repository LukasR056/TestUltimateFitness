package at.fh.swenga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.ExerciseModel;
import at.fh.swenga.model.UserModel;

@Repository
@Transactional
public interface UserQueryRepository extends JpaRepository<UserModel, Integer> {

	UserModel findByUserName(String searchString);
	
	UserModel findByEMail(String searchString);

	UserModel getUserByUserName(String searchString);

	
	@Query("SELECT u FROM UserModel u WHERE u.coach = null OR u.coach = '' ")
	public List<UserModel> findCoach();

	List<UserModel> findByCoach(String searchString);
	
	@Query("SELECT COUNT(u) FROM UserModel u WHERE u.userName = :userName AND u.eMail = :eMail")
	public int existingUser(@Param ("userName") String userName, @Param ("eMail") String eMail);


	
}
