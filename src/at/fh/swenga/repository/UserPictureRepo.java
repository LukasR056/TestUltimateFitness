package at.fh.swenga.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.fh.swenga.model.PictureModel;
import at.fh.swenga.model.UserModel;
import at.fh.swenga.model.UserPictureModel;

@Repository
@Transactional
public interface UserPictureRepo extends JpaRepository<UserPictureModel, Integer>
{

	List<UserPictureModel> findByUser(UserModel user);

	UserPictureModel findByUserAndPicture(UserModel user, PictureModel pic);

	List<UserPictureModel> findByUserAndPictureLevel(UserModel user, String string);

	List<UserPictureModel> findByPictureLevel(String string);

	void removeByAmount(int i);


		

}
