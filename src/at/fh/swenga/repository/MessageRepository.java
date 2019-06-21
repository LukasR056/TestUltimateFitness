package at.fh.swenga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.ExerciseModel;
import at.fh.swenga.model.MessageModel;
import at.fh.swenga.model.PictureModel;
import at.fh.swenga.model.UserModel;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<MessageModel, Integer> {

	List<MessageModel> findByToUser(UserModel user);
	
	MessageModel findById(int id);

}
