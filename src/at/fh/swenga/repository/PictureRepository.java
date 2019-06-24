package at.fh.swenga.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.PictureModel;

@Repository
@Transactional
public interface PictureRepository extends JpaRepository<PictureModel, Integer> {

	List<PictureModel> findByLevel(String string);

	PictureModel findByName(String string);

	PictureModel findByPictureId(int pictureId);

	
}
