package at.fh.swenga.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.model.PictureModel;


@Repository
@Transactional		
public class PictureDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public PictureModel merge(PictureModel pic) {
		return entityManager.merge(pic);
	}

	
}
