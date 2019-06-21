package at.fh.swenga.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.ForumentryModel;

@Repository
@Transactional
public interface ForumentryRepository extends JpaRepository<ForumentryModel, Integer>{

	ForumentryModel findTop1ByThreadOrderByCreateDate(String thread);
	
	ForumentryModel findTop1ByThreadOrderByIdDesc(String thread);
	
	List<ForumentryModel> findByThreadOrderByIdDesc(String thread);
	
	@Query("SELECT COUNT(f) FROM ForumentryModel f WHERE f.thread = :thread")
	public int threadSize(@Param("thread") String thread);
	
	
	@Query("DELETE FROM ForumentryModel f WHERE f.id = :id")
	public void deleteForumentry(@Param("id") int id);
	
	 
}
