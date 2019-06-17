package at.fh.swenga.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.JoinColumn; 

@Entity
@Table(name = "User")
public class UserModel implements java.io.Serializable {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public int id;
	
	

	@Column(nullable = false, length = 40)
	private String firstName;
	
	@Column(nullable = false, length = 40)
	private String lastName;
	
	@Column(unique = true, nullable = false, length = 15)
	public String userName;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	// @DateTimeFormat(pattern = "dd.MM.yyyy")
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
	
	@Column(unique = true)
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
	
	@Column()
	private boolean isAdmin;
	
	@Column(nullable=false)
	private boolean enabled;
	
	@Column(nullable=false)
	private String password;
	
	@Version
	long version;
	
	
	
	//Relations
	// ManyToMany
	// https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE })
    @JoinTable(name = "userExercises",
        joinColumns = @JoinColumn(name = "userId"), // Table Name + id???
        inverseJoinColumns = @JoinColumn(name = "exerciseId") )
	private Set<ExerciseModel> exercises = new HashSet<>(); // = new HashSet<>(); NOTWENDIG? */
	
	// fuer User Roles!
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "userRoles", joinColumns = @JoinColumn(name = "userId"),
       inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<RoleModel> roles;

	
	@OneToMany(mappedBy="userId",fetch=FetchType.LAZY)
	@OrderBy("id")
	private Set<LogModel> logs; 
	
	@OneToMany(mappedBy="userId",fetch=FetchType.LAZY)
	@OrderBy("id")
	private Set<ForumentryModel> entries;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<UserPicturesModel> userPictures;
	

	public UserModel ()
	{
		
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	


	public UserModel(String firstName, String lastName, String userName, Date birthDate, String gender, double height,
			double weight, String coach, String eMail, int points, boolean isAdmin, boolean enabled, String password) {
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
		this.isAdmin = isAdmin;
		this.enabled = enabled;
		this.password = password;
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

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<ExerciseModel> getExercises() {
		return exercises;
	}


	public void setExercises(Set<ExerciseModel> exercises) {
		this.exercises = exercises;
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


	public Set<UserPicturesModel> getUserPictures() {
		return userPictures;
	}


	public void setUserPictures(Set<UserPicturesModel> userPictures) {
		this.userPictures = userPictures;
	}


	public String getGender() {
		return gender;
	}

	
	
	public boolean add(UserPicturesModel e) {
		return userPictures.add(e);
	}


	public boolean remove(Object o) {
		return userPictures.remove(o);
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
	
	public void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + id;
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
		if (id != other.id)
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
				+ ", coach=" + coach + ", eMail=" + eMail + ", points=" + points + ", isAdmin=" + isAdmin + ", enabled="
				+ enabled + ", password=" + password + "]";
	}

}
