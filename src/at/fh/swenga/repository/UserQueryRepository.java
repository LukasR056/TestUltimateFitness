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
	
	UserModel findByEMail(String searchString);

	UserModel getUserByUserName(String searchString);

	
	@Query("SELECT u FROM UserModel u WHERE u.coach = null")
	public List<UserModel> findCoach();
		

	
}
