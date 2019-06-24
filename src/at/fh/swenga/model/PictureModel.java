package at.fh.swenga.model;

import java.util.Arrays;
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "Picture")
public class PictureModel {

	@Id
	@Column(name = "pictureId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pictureId;
	

	// OneToMany
	@OneToMany(mappedBy = "picture",fetch=FetchType.EAGER)
	@OrderBy("id")
	private Set<UserPictureModel> userPictures;

	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String level;
	/* Auswahl aus vorgegebener Liste z.B.
	 * public String[] levelList = {"Bronze", "Silber", "Gold", "Platin"};
	 */
	
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] picture;
	
	
	public PictureModel() {
		
	}

	// mit this.userPictures = userPictures;??
	
	/*public PictureModel(int id, String name, String level, byte[] picture) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.picture = picture;
	}*/

	public PictureModel(int pictureId, String name, String level) {
		super();
		this.pictureId = pictureId;
		this.name = name;
		this.level = level;
	}

	// Getter & Setter
	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	

	public Set<UserPictureModel> getUserPictures() {
		return userPictures;
	}

	public void setUserPictures(Set<UserPictureModel> userPictures) {
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
		result = prime * result + pictureId;
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
		if (pictureId != other.pictureId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PictureModel [pictureId=" + pictureId + ", name=" + name + ", level=" + level + ", picture="
				+ Arrays.toString(picture) + "]";
	}

	

	/*@Override
	public String toString() {
		return "PictureModel [id=" + id + ", userPictures=" + userPictures + ", name=" + name + ", level=" + level
				+ ", picture=" + Arrays.toString(picture) + "]";
	}*/
	
/*	public void addUserPicture(UserPictureModel userPicture) {
		if (userPictures==null) {
			userPictures= new HashSet<UserPictureModel>();
		}
		userPictures.add(userPicture);
	}  */
	
	
}
