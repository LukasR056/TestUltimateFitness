 package at.fh.swenga.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.model.ExerciseModel;
import at.fh.swenga.model.UserModel;

@Repository
@Transactional
public interface ExerciseRepository  extends JpaRepository<ExerciseModel, Integer>{

	ExerciseModel findByName(String name);

	List<ExerciseModel> findByType(String searchType);

	ExerciseModel findById (int id);

	


	
	
}
