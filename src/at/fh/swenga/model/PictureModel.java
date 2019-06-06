package at.fh.swenga.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Picture")
public class PictureModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// OneToMany
	@OneToMany(mappedBy = "picture", cascade = CascadeType.ALL, 
			fetch=FetchType.LAZY, orphanRemoval = true)
	private Set<UserPicturesModel> userPictures;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String level;
	/* Auswahl aus vorgegebener Liste z.B.
	 * public String[] levelList = {"Bronze", "Silber", "Gold", "Platin"};
	 */
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] picture;
	
	
	public PictureModel() {
		// TODO Auto-generated constructor stub
	}

	
	public PictureModel(int id, Set<UserPicturesModel> userPictures, String name, String level, byte[] picture) {
		super();
		this.id = id;
		this.userPictures = userPictures;
		this.name = name;
		this.level = level;
		this.picture = picture;
	}




	// Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<UserPicturesModel> getUserPictures() {
		return userPictures;
	}

	public void setUserPictures(Set<UserPicturesModel> userPictures) {
		this.userPictures = userPictures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
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
		PictureModel other = (PictureModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
