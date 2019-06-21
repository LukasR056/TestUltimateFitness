package at.fh.swenga.repository;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
 
import org.springframework.stereotype.Repository;
 
import at.fh.swenga.model.RoleModel;
 
@Repository
@Transactional          
public class RoleRepository  {
      
       @PersistenceContext
       protected EntityManager entityManager;
      
      
       List<RoleModel> users = new ArrayList<RoleModel>();
 
      
       public  List<RoleModel> getAllRoles() {
             TypedQuery<RoleModel> typedQuery = entityManager.createQuery("select r from RoleModel r",
                           RoleModel.class);
             List<RoleModel> typedResultList = typedQuery.getResultList();
             return typedResultList;
       }
       
       public RoleModel getRole(String role) {
    	   try {
    		   TypedQuery<RoleModel> typedQuery = entityManager
    				   .createQuery("select r from RoleModel r where r.role = :role", RoleModel.class);
    		   typedQuery.setParameter("role", role);
    		   return typedQuery.getSingleResult();
    	   }
    	   catch (NoResultException e) {
    		   return null;
    	   }
       }
 
       public void persist(RoleModel role) {
             entityManager.persist(role);
            
       }
 
      
      
 
}