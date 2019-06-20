package at.fh.swenga.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "Forumentry")
public class ForumentryModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column()
	public String thread;
	@Column()
	private String title;
	@Column()
	private String text;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date createDate;
	
	public void setUser(UserModel user) {
		this.user = user;
	}

	@ManyToOne (cascade = CascadeType.PERSIST)
	private UserModel user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public UserModel getUser() {
		return user;
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
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public ForumentryModel(String thread, String title, String text, Date createDate, UserModel user) {
		super();
		this.thread = thread;
		this.title = title;
		this.text = text;
		this.createDate = createDate;
		this.user = user;
	}


	public ForumentryModel(int id, String thread, String title, String text, Date createDate, UserModel user) {
		super();
		this.id = id;
		this.thread = thread;
		this.title = title;
		this.text = text;
		this.createDate = createDate;
		this.user = user;
	}

	public ForumentryModel() {

	}

	@Override
	public String toString() {
		return "ForumentryModel [id=" + id + ", thread=" + thread + ", title=" + title + ", text=" + text
				+ ", createDate=" + createDate + ", user=" + user + "]";
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

