package at.fh.swenga.model;

import java.util.HashSet;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// ManyToMany
	@ManyToMany(mappedBy = "exercises")
	private Set<UserModel> users = new HashSet<>(); // = new HashSet<>(); NOTWENDIG?
	
		
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String type;
	/* Auswahl aus vorgegebener Liste z.B.
	 * public String[] typesList = {"Brust", "Schulter", "Bizeps", "Trizeps", "Bauch", "Beine", "Po"};
	 */
	
	@Lob // noch genauer betrachten! Lob ist für Large Objects in einer Datenbank
	@Basic(fetch = FetchType.LAZY) // Lob sollte mit Basic kombiniert werden
	// whs um Daten nur zu fetchen, wenn diese tatsächlich benötigt werden
	private byte[] video;
	
	@Column(nullable = false, length = 500)
	private String description;
	
	public ExerciseModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ExerciseModel(int id, String name, String type, byte[] video, String description) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.video = video;
		this.description = description;
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
