package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class ElementList extends Model {
	
	public String label;
	
	@ManyToOne
    public User owner;	
	
	@OneToMany(mappedBy="list", cascade=CascadeType.ALL)
	public List<Element> elements;

	public ElementList(User owner, String label){
		this.owner = owner;
		this.label = label;
		this.elements = new ArrayList<Element>();
	}
	
	public void addElement(String label){
		Element element = new Element(this, label);
		this.elements.add(element);
		this.save();
		return;
	}
}
