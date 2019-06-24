package at.fh.swenga.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.model.UserModel;

@Repository
@Transactional		
public class UserRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	List<UserModel> users = new ArrayList<UserModel>();

	
	public  List<UserModel> getUsers() {
		TypedQuery<UserModel> typedQuery = entityManager.createQuery("select u from UserModel u",
				UserModel.class);
		List<UserModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}

	public UserModel update (UserModel user) {
	    return entityManager.merge(user);
	}

	public void persist(UserModel user) {
		entityManager.persist(user);
		
	}
	public UserModel merge(UserModel user) {
		return entityManager.merge(user);
	}

	public void addUser(UserModel user) {
		users.add(user);
	}
	
}
