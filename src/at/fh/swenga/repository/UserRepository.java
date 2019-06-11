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
@Transactional		//extends JpaRepository<UserModel, Integer>
public class UserRepository  {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	
	List<UserModel> users = new ArrayList<UserModel>();

	
	// von der Übung raus
	public  List<UserModel> getUsers() {
		TypedQuery<UserModel> typedQuery = entityManager.createQuery("select u from UserModel u",
				UserModel.class);
		List<UserModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}

	public void persist(UserModel user) {
		entityManager.persist(user);
		
	}

	
	
	
 /*	public List<UserModel> searchUsers(String searchString) {
		TypedQuery<UserModel> typedQuery = entityManager.createQuery(
				"select u from UserModel u where u.firstName like :search or u.lastName like :search", 
				UserModel.class);
		typedQuery.setParameter("search", "%" + searchString + "%");
		List<UserModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}  weitere Queries von der Übung Woche 5 JPA*/

	
	
	
	
	
	

}
