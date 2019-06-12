package at.fh.swenga.repository;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
 
import org.springframework.stereotype.Repository;
 
import at.fh.swenga.model.RoleModel;
 
@Repository
@Transactional            //extends JpaRepository<UserModel, Integer>
public class RoleRepository  {
      
       @PersistenceContext
       protected EntityManager entityManager;
      
      
       List<RoleModel> users = new ArrayList<RoleModel>();
 
      
       public  List<RoleModel> getRoles() {
             TypedQuery<RoleModel> typedQuery = entityManager.createQuery("select r from RoleModel r",
                           RoleModel.class);
             List<RoleModel> typedResultList = typedQuery.getResultList();
             return typedResultList;
       }
 
       public void persist(RoleModel role) {
             entityManager.persist(role);
            
       }
 
      
      
      
/*    public List<UserModel> searchUsers(String searchString) {
             TypedQuery<UserModel> typedQuery = entityManager.createQuery(
                           "select u from UserModel u where u.firstName like :search or u.lastName like :search",
                           UserModel.class);
             typedQuery.setParameter("search", "%" + searchString + "%");
             List<UserModel> typedResultList = typedQuery.getResultList();
             return typedResultList;
       }  weitere Queries von der Übung Woche 5 JPA*/
 
      
      
      
      
      
      
 
}