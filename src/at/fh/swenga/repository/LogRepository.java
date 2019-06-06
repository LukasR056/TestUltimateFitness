package at.fh.swenga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.model.LogModel;

@Repository
public interface LogRepository extends JpaRepository<LogModel, Integer>{

}
