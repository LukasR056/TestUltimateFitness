package at.fh.swenga.model;
 
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
 
@Entity
@Table(name = "Role")
public class RoleModel implements java.io.Serializable {
               
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                @Column(name = "roleId")
                private int id;
 
                @Column(nullable = false)
                private String role;
                
                @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
            	private Set<UserModel> users;
 
                              
                public RoleModel() {
                       
                }
 
                public RoleModel(String role) {
                       super();
                       this.role = role;
                }
 
                public int getId() {
                       return id;
                }
 
                public void setId(int id) {
                       this.id = id;
                }
 
                public String getRole() {
                       return role;
                }
 
                public void setRole(String role) {
                       this.role = role;
                }
 
                public Set<UserModel> getUsers() {
					return users;
				}

				public void setUsers(Set<UserModel> users) {
					this.users = users;
				}
				
				public void addUser(UserModel user) {
					if (users == null) users = new HashSet<UserModel>();
					users.add(user);
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
                       return "RoleModel [role=" + role + "]";
                }
               
               
}