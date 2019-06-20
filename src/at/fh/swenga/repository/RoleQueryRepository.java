package at.fh.swenga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.RoleModel;

@Repository
@Transactional
public interface RoleQueryRepository extends JpaRepository<RoleModel, Integer>{
	
	RoleModel findFirstRoleById(int id);
	
}
