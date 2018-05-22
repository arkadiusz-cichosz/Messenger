package pl.pwpw.messenger.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User implements Serializable {

	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private int age;
	
	@OneToMany(mappedBy="owner")
	private List<Message> messages = new ArrayList();
	
	public User() {
		super();
	}
	
	public User(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(Message message) {
		messages.add(message);
	}
	
	@Override
	public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("UserDetails: [\n ");
			stringBuilder.append("First Name=" + firstName + " \n");
			stringBuilder.append("Last Name=" + lastName + " \n");
			stringBuilder.append("Age=" + age + "\n");
			stringBuilder.append("Number messages=" + messages.size() + " \n");
			stringBuilder.append("] \n");
			return stringBuilder.toString();
	}
}
