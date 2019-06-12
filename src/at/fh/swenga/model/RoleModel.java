package at.fh.swenga.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "Role")
public class RoleModel implements java.io.Serializable {
               
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                @Column(name = "roleId")
                private int id;
 
                @Column(nullable = false)
                private String name;
 
                              
                public RoleModel() {
                               // TODO Auto-generated constructor stub
                }
 
                public RoleModel(String name) {
                               super();
                               this.name = name;
                }
 
                public int getId() {
                               return id;
                }
 
                public void setId(int id) {
                               this.id = id;
                }
 
                public String getName() {
                               return name;
                }
 
                public void setName(String name) {
                               this.name = name;
                }
 
                @Override
                public int hashCode() {
                               final int prime = 31;
                               int result = 1;
                               result = prime * result + id;
                               return result;
                }
 
                @Override
                public boolean equals(Object obj) {
                               if (this == obj)
                                               return true;
                               if (obj == null)
                                               return false;
                               if (getClass() != obj.getClass())
                                               return false;
                               RoleModel other = (RoleModel) obj;
                               if (id != other.id)
                                               return false;
                               return true;
                }
 
                @Override
                public String toString() {
                               return "RoleModel [name=" + name + "]";
                }
               
               
}