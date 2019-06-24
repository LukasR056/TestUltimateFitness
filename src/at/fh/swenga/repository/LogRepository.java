package at.fh.swenga.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.LogModel;

@Repository
@Transactional
public interface LogRepository extends JpaRepository<LogModel, Integer> {

	
}

