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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.JoinColumn; 

@Entity
@Table(name = "User")
public class UserModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public int id;
	
	

	@Column(nullable = false, length = 40)
	private String firstName;
	
	@Column(nullable = false, length = 40)
	private String lastName;
	
	@Column(unique = true, nullable = false, length = 15)
	public String userName;
	
	@Temporal(TemporalType.DATE)
	public Date birthDate;
	
	@Column(nullable = false, length = 1)
	private String gender;
	
	@Column(nullable = false)
	private double height;
	
	@Column(nullable = false)
	private double weight;
	
	@Column()
	private int coach;
	
	@Column(unique = true)
	private String eMail;
	
 /*	@Column(nullable = true)
	private double bmi;  wird �ber thymeleaf berechnet*/
	
	
	
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
	
	@Column()
	private boolean enabled;
	
	@Column(nullable=false)
	private String password;
	
	@Version
	long version;
	
	// diese Beziehung wird ben�igt f�r die m:n mit die exercise
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinTable(
		      name="User_Exercise",
		      joinColumns={
		    		  @JoinColumn(name="User_id", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="Exercise_id", referencedColumnName="id")})
	private List<ExerciseModel> exercises;
	
	
/*	@OneToMany(mappedBy="userId",fetch=FetchType.LAZY)
	@OrderBy("id")
	private Set<LogModel> logs; */
	
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


	@OneToMany(mappedBy="userId",fetch=FetchType.LAZY)
	@OrderBy("id")
	private Set<ForumentryModel> entries;
	
	/*@OneToMany(mappedBy="user", cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<UserPicturesModel> userPictures; */
	

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
			double weight, int coach, String eMail, int points, boolean isAdmin, boolean enabled, String password) {
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
	
	


	public List<ExerciseModel> getExercises() {
		return exercises;
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

	public int getCoach() {
		return coach;
	}

	public void setCoach(int coach) {
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
	
	

/*	public Set<LogModel> getLogs() {
		return logs;
	}


	public void setLogs(Set<LogModel> logs) {
		this.logs = logs;
	}*/


	public Set<ForumentryModel> getEntries() {
		return entries;
	}


	public void setEntries(Set<ForumentryModel> entries) {
		this.entries = entries;
	}


	


	public String getGender() {
		return gender;
	}

	
	
	
	 
    /* public void addTag(PictureModel picture) {
        UserPicturesModel userPicture = new UserPicturesModel(this, picture);
        userPictures.add(userPicture);
        picture.getPicture().add(userPicture);
    }
 
    public void removeTag(Tag tag) {
        for (Iterator<PostTag> iterator = tags.iterator();
             iterator.hasNext(); ) {
            PostTag postTag = iterator.next();
 
            if (postTag.getPost().equals(this) &&
                    postTag.getTag().equals(tag)) {
                iterator.remove();
                postTag.getTag().getPosts().remove(postTag);
                postTag.setPost(null);
                postTag.setTag(null);
            }
        }
    } */ // WIRD DAS �BERHAUPT BEN�TIGT???	

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
				+ enabled + ", password=" + password + ", exercises=" + exercises + "]";
	}


	

	

}
