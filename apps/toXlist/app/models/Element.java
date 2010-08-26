package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Element extends Model {

	public String label;
	public boolean isDone;
	
	@ManyToOne
	public ElementList list;
	
	public Element(ElementList list, String label){
		this.label = label;
		this.isDone = false;
		this.list = list;
	}
	
	public void update(){
		this.isDone = !this.isDone;
	}
}
