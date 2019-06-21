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

import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "Message")
public class MessageModel {
	
	
	@Id
	@Column(name = "messageId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 20)
	private String subject;
	
	@ManyToOne
	UserModel fromUser;
	
	@ManyToOne
	UserModel toUser;
	
	@Column(length = 250)
	private String message;

	
	public MessageModel()
	{
		
	}
	

	public MessageModel( String subject, UserModel fromUser, UserModel toUser, String message) {
		super();
		this.subject = subject;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public UserModel getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserModel fromUser) {
		this.fromUser = fromUser;
	}

	public UserModel getToUser() {
		return toUser;
	}

	public void setToUser(UserModel toUser) {
		this.toUser = toUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
		MessageModel other = (MessageModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessageModel [id=" + id + ", subject=" + subject + ", fromUser=" + fromUser + ", toUser=" + toUser
				+ ", message=" + message + "]";
	}
	
	
	

}
