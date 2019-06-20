package at.fh.swenga.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import at.fh.swenga.model.UserPictureModel;

@Repository
@Transactional		
public class UserPictureDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public UserPictureModel merge(UserPictureModel UserPicture) {
		return entityManager.merge(UserPicture);
	}

	public void persist(UserPictureModel newUserPic) {
		entityManager.persist(newUserPic);
		
	}
	
/*	public void addUserPicture(int userId, int pictureId, int amount)
	{
		Query q = entityManager.createNativeQuery("insert into UserPictures (user_userId,picture_id,amount) values (:userId,:pictureId,:amount)");
		q.setParameter("userId", userId);
		q.setParameter("pictureId", pictureId);
		q.setParameter("amount", amount);
		q.executeUpdate();
		
		
	}*/

}
