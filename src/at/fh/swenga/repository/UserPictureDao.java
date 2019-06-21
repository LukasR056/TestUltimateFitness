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
	
	
	public void deleteUserPicture(int amount)
	{
		Query q = entityManager.createNativeQuery("delete from UserPictures where amount = amount");;
		q.setParameter("amount", amount);
		q.executeUpdate();
		
		
	}

}
