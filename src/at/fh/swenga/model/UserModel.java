package at.fh.swenga.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 

@Entity
@Table(name = "User")
public class UserModel implements java.io.Serializable {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int userId;
	

	@Column(nullable = false, length = 40)
	private String firstName;
	
	@Column(nullable = false, length = 40)
	private String lastName;
	
	@Column(unique = true, nullable = false, length = 15)
	public String userName;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date birthDate;
	
	@Column(nullable = false, length = 1)
	private String gender;
	
	@Column(nullable = false)
	private double height;
	
	@Column(nullable = false)
	private double weight;
	
	@Column()
	private String coach;
	// AUF USERNAME umgeändert damit dieser zugewiesen werden kann
	// ist td eindeutig! DEFAULT ist man selber Coach -> null
	
	@Column(nullable = false, unique = true)
	private String eMail;
	
 /*	@Column(nullable = true)
	private double bmi;  wird ï¿½ber thymeleaf berechnet*/
	
	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}

	@Column()
	private int points = 0;
	

	
	@Column(nullable=false)
	private boolean enabled;
	
	@Column(nullable=false)
	private String password;
	
	@Column
	private String passwordConfirmed;
	
	@Version
	long version;
	
	
	
	//Relations
	// ManyToMany
  // diese Beziehung wird benoeigt fuer die m:n mit die exercise
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinTable(
		      name="User_Exercise",
		      joinColumns={
		    		  @JoinColumn(name="User_id", referencedColumnName="userId")},
		      inverseJoinColumns={@JoinColumn(name="Exercise_id", referencedColumnName="id")})
	private List<ExerciseModel> exercises;
	
  
	// fuer User Roles!
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinTable(name = "userRoles", joinColumns = @JoinColumn(name = "userId"),
       inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<RoleModel> roles;

	

	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	@OrderBy("id")
	private Set<LogModel> logs; 
	
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	@OrderBy("id")
	private Set<ForumentryModel> entries;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
	@OrderBy("id")
	private Set<UserPictureModel> userPicture;
	

	@OneToMany(mappedBy="message",fetch=FetchType.EAGER)
	@OrderBy("id")
	private Set<MessageModel> messages; 
	

	
  
  public Set<MessageModel> getMessages() {
		return messages;
	}


	public void setMessages(Set<MessageModel> messages) {
		this.messages = messages;
	}


	// Luki Exercises
	public void setExercises(List<ExerciseModel> exercises) {
		this.exercises = exercises;
	}
	
	public void addExercise(ExerciseModel exercise) {
		if (exercises== null) {
			exercises= new ArrayList<ExerciseModel>();
		}
		exercises.add(exercise);
		
	}
	

	public ExerciseModel remove(int index) {
		return exercises.remove(index);
	}
  
  public List<ExerciseModel> getExercises() {
		return exercises;
	}
  // Luki Exercises

  

	public UserModel ()
	{
		
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}

	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}

	



	public UserModel(int userId, String firstName, String lastName, String userName, Date birthDate, String gender,
			double height, double weight, String coach, String eMail, int points, boolean enabled,
			String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.coach = coach;
		this.eMail = eMail;
		this.points = points;
		this.enabled = enabled;
		this.password = password;
	}
	
	public UserModel(String firstName, String lastName, String userName, Date birthDate, String gender, double height,
			double weight, String coach, String eMail, int points, boolean enabled, String password, String passwordConfirmed) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.coach = coach;
		this.eMail = eMail;
		this.points = points;
		this.enabled = enabled;
		this.password = password;
		this.passwordConfirmed = passwordConfirmed;
	}
	
	


	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String isGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCoach() {
		return coach;
	}


	public Set<UserPictureModel> getUserPicture() {
		return userPicture;
	}


	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Set<LogModel> getLogs() {
		return logs;
	}


	public void setLogs(Set<LogModel> logs) {
		this.logs = logs;
	}


	public Set<ForumentryModel> getEntries() {
		return entries;
	}


	public void setEntries(Set<ForumentryModel> entries) {
		this.entries = entries;
	}


	


	public void setUserPicture(Set<UserPictureModel> userPicture) {
		this.userPicture = userPicture;
	}


	public String getGender() {
		return gender;
	}

	
	
	public boolean add(UserPictureModel e) {
		return userPicture.add(e);
	}


	public boolean remove(Object o) {
		return userPicture.remove(o);
	}	

	public Set<RoleModel> getRoles() {
		return roles;
	}


	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public void addRoleModel(RoleModel role) {
		if (roles == null) roles = new HashSet<RoleModel>();
		roles.add(role);
	}
	
	public void removeRoleModel(RoleModel role) {
		roles.remove(role);
	}
	
	public void addLogModel(LogModel log) {
		if (logs == null) logs = new HashSet<LogModel>();
		logs.add(log);
	}
	
	public void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserModel other = (UserModel) obj;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserModel [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", birthDate=" + birthDate + ", gender=" + gender + ", height=" + height + ", weight=" + weight
				+ ", coach=" + coach + ", eMail=" + eMail + ", points=" + points  + ", enabled="
				+ enabled + ", password=" + password + ", passwordConfirmed=" + passwordConfirmed + "]";
	}
	
	//wurde für die UserPicture hinzugefügt!!!!
	/*public void addUserPicture(UserPictureModel userPicture) {
		if (userPicture==null) {
			userPicture= new HashSet<UserPictureModel>();
		}
		userPicture.add(userPicture);
	}*/
	
	

}

