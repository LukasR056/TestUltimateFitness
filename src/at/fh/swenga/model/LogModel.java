package at.fh.swenga.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LogModel implements Serializable {

	@Id
	@Column(name = "logId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;

	@Column(nullable = false)
	private double height;

	@Column(nullable = false)
	private double weight;

	@Column
	private int points = 0;

	@Column
	private Date date;

	@ManyToOne // (cascade = CascadeType.ALL)
	UserModel user;

	public LogModel() {

	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public LogModel(double height, double weight, int points, Date date) {
		super();
		this.height = height;
		this.weight = weight;
		this.points = points;
		this.date = date;
	}

	public LogModel(UserModel user, double height, double weight, int points, Date date) {
		super();
		this.user = user;
		this.height = height;
		this.weight = weight;
		this.points = points;
		this.date = date;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + logId;
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
		LogModel other = (LogModel) obj;
		if (logId != other.logId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LogModel [user=" + user + ", height=" + height + ", weight=" + weight + ", points=" + points + ", date="
				+ date + "]";
	}


}