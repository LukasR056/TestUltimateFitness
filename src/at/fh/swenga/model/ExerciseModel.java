package at.fh.swenga.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Exercise")
public class ExerciseModel {

	//hier fehlt nur mehr die Videos der rest passt so f�r die Beziehungen.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// ManyToMany
	@ManyToMany(mappedBy = "exercises",fetch=FetchType.EAGER)
	private List<UserModel> users ; 
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 10)
	private String type;
	/* Auswahl aus vorgegebener Liste z.B.
	 * public String[] typesList = {"Brust", "Schulter", "Bauch", "Beine"};
	 */
	
	@Lob // noch genauer betrachten! Lob ist f�r Large Objects in einer Datenbank
	@Basic(fetch = FetchType.LAZY) // Lob sollte mit Basic kombiniert werden
	// whs um Daten nur zu fetchen, wenn diese tats�chlich ben�tigt werden
	@Column(nullable = true)
	private byte[] video;
	
	@Column(nullable = false, length = 500)
	private String description;
	
	public ExerciseModel() {
		
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getVideo() {
		return video;
	}

	public void setVideo(byte[] video) {
		this.video = video;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<UserModel> getUsers() {
		return users;
	}





	public ExerciseModel(String name, String type, byte[] video, String description) {
		super();
		this.name = name;
		this.type = type;
		this.video = video;
		this.description = description;
	} 
	
	public ExerciseModel(int id, List<UserModel> users, String name, String type, byte[] video, String description) {
		super();
		this.id = id;
		this.users = users;
		this.name = name;
		this.type = type;
		this.video = video;
		this.description = description;
	}





	public void setUsers(List<UserModel> users) {
		this.users = users;
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
		ExerciseModel other = (ExerciseModel) obj;
		if (id != other.id)
			return false;
		return true;
	}





	
	
	
}
