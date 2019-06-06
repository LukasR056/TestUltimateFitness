package at.fh.swenga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.model.ForumentryModel;

@Repository
public interface ForumentryRepository extends JpaRepository<ForumentryModel, Integer>{

	
}
