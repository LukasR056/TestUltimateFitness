package at.fh.swenga.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserPicture")
public class UserPictureModel {
	
	@Id
	@Column (name="userPictureId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userPictureId;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="User_userId", referencedColumnName = "userId")
	UserModel user;
	
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="Picture_pictureId", referencedColumnName = "pictureId")
	PictureModel picture; 
	
	private int amount;
	
	
	public UserPictureModel() {
		
	}
	
	
	
	public UserPictureModel(int amount) {
		super();
		this.amount = amount;
	}
	
		public UserPictureModel(UserModel user, PictureModel picture, int amount) {
		super();
		this.user = user;
		this.picture = picture;
		this.amount = amount;
	} 
	

	// Getter & Setter
	public int getUserPictureId() {
		return userPictureId;
	}


	public void setId(int userPictureId) {
		this.userPictureId = userPictureId;
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
		result = prime * result + userPictureId;
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
		UserPictureModel other = (UserPictureModel) obj;
		if (userPictureId != other.userPictureId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserPictureModel [userPictureId=" + userPictureId + ", user=" + user 
				+ ", amount=" + amount + "]";
	}
	
	
}
