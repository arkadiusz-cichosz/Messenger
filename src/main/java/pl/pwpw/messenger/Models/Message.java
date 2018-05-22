package pl.pwpw.messenger.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.executable.ValidateOnExecution;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Messages")
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	long id;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date created;
	
	private String title;
	
	private String content;
	
	@Enumerated(EnumType.STRING)
	private Topic topic;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Message parent;
	
	public Message() {
		super();
	}
	
	public Message(String title, User owner, String content, Topic topic) {
		this.title = title;
		this.owner = owner;
		this.content = content;
		this.topic = topic;
	}
	
	public Message(String title, User owner, String content, Topic topic, Message parent) {
		this(title, owner, content, topic);
		this.parent = parent;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Message getParent() {
		return parent;
	}

	public void setParent(Message parent) {
		this.parent = parent;
	}

	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Topic getTopic() {
		return topic;
	}
	
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("MessageDetails: [ \n");
		stringBuilder.append("Topic=" + topic + " \n");
		stringBuilder.append("Title=" + title + " \n");
		stringBuilder.append("Content=" + content + " \n");
		stringBuilder.append("Created=" + created + " \n");
		stringBuilder.append("Owner=" + owner + " \n");
		stringBuilder.append("Parent=" + parent + " \n");
		stringBuilder.append("] \n");
		return stringBuilder.toString();
	}
}
