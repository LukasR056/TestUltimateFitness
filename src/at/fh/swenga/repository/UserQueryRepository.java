package at.fh.swenga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import at.fh.swenga.model.UserModel;

@Repository
@Transactional
public interface UserQueryRepository extends JpaRepository<UserModel, Integer> {

	UserModel findByUserName(String searchString);


	
}
