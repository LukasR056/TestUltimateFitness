package at.fh.swenga.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;



@Entity
@Table(name = "Forumentry")
public class ForumentryModel {

	@Id
	@Column(name = "id")
	private int id;

	@Column()
	public String thread;
	@Column()
	private String title;
	@Column()
	private String text;
	
	public void setUserId(UserModel userId) {
		this.userId = userId;
	}

	@ManyToOne (cascade = CascadeType.PERSIST)
	private UserModel userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public UserModel getUserId() {
		return userId;
	}

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public ForumentryModel(int id, String thread, String title, String text, UserModel userId) {
		super();
		this.id = id;
		this.thread = thread;
		this.title = title;
		this.text = text;
		this.userId = userId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ForumentryModel() {

	}

	@Override
	public String toString() {
		return "ForumentryModel [id=" + id + ", thread=" + thread + ", title=" + title + ", text=" + text + ", userId="
				+ userId + "]";
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
		ForumentryModel other = (ForumentryModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
