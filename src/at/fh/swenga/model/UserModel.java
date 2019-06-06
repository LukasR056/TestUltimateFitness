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
import javax.persistence.JoinColumn; 

@Entity
@Table(name = "User")
public class UserModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// von Janik hinzugefügt, sonst gibt es keine Autogenerierung (meiner Meinung nach)
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
	
	@Column(nullable = false)
	private double bmi;
	
	@Column()
	private boolean isAdmin;
	
	@Column()
	private boolean enabled;
	
	// ManyToMany
	// https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE })
    @JoinTable(name = "user_exercises",
        joinColumns = @JoinColumn(name = "user_id"), // Table Name + id???
        inverseJoinColumns = @JoinColumn(name = "exercise_id") )
	private Set<ExerciseModel> exercises = new HashSet<>(); // = new HashSet<>(); NOTWENDIG? */
	
	
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
	
	
	public UserModel(String firstName, String lastName, 
			String userName, Date birthDate, String gender,
			double height, double weight, int coach, String eMail, 
			double bmi, boolean isAdmin, boolean enabled) {
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
		this.bmi = bmi;
		this.isAdmin = isAdmin;
		this.enabled = enabled;
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

	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
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
    } */ // WIRD DAS ÜBERHAUPT BENÖTIGT???	

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
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", birthDate=" + birthDate + ", gender=" + gender + ", height=" + height + ", weight=" + weight
				+ ", coach=" + coach + ", eMail=" + eMail + ", bmi=" + bmi + ", isAdmin=" + isAdmin + ", enabled="
				+ enabled + "]";
	}

}
