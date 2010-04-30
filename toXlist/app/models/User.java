package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {

	@Required
	public String login;
	
	@Required
	public String password;

	@Required
	public String name;
	
	@OneToMany(mappedBy="owner", cascade = CascadeType.ALL)
	public List<ElementList> lists;
	
	public User(String login, String password, String name) {
		this.login = login; 
		this.password = password;
		this.name = name;
	}

	public static User connect(String login, String password) {
		return User.find("byLoginAndPassword", login, password).first();
	}
	
	public String toString(){
		return this.name;
	}
}
