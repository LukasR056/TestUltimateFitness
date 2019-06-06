package at.fh.swenga.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "UserPictures")
public class UserPicturesModel {
	
	// NICHT SICHER OB DAS SO RICHTIG IST!!!
	// https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserModel user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private PictureModel picture;
	
	private int amount;
	
	
	public UserPicturesModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserPicturesModel(int id, UserModel user, PictureModel picture, int amount) {
		super();
		this.id = id;
		this.user = user;
		this.picture = picture;
		this.amount = amount;
	}

	// Getter & Setter
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public UserModel getUser() {
		return user;
	}


	public void setUser(UserModel user) {
		this.user = user;
	}


	public PictureModel getPicture() {
		return picture;
	}


	public void setPicture(PictureModel picture) {
		this.picture = picture;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}

	// HashCode & Equals
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
		UserPicturesModel other = (UserPicturesModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
