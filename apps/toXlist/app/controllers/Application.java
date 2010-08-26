package controllers;

import java.util.List;

import models.Element;
import models.ElementList;
import models.User;
import play.Play;
import play.mvc.*;

public class Application extends Controller {

	@Before
	static void addDefaults() {
	    renderArgs.put("title", Play.configuration.getProperty("site.title"));
	    renderArgs.put("baseline", Play.configuration.getProperty("site.baseline"));
	}
	
    public static void index() {
    	
    	User u = User.find("byName", "jojo").first();    	
    	List<ElementList> elementLists = ElementList.find("byOwner", u).from(1).fetch(10);
    	
        render(elementLists);
    }

    public static void createList(String label){
    	User u = User.find("byName", "jojo").first();
    	new ElementList(u, label).save();
    	
    	index();
    }
    
    public static void deleteList(Long listId){    	
    	ElementList list = ElementList.find("byId", listId).first();
    	list.delete();
    	
    	index();
    }
    
    public static void showList(Long listId){
    	ElementList list = ElementList.find("byId", listId).first();
    	List<Element> elements = Element.find("list.id", listId).from(0).fetch(10);
    	render(list, elements);
    }    
 
    public static void updateElement(Long elementId){
    	Element element = Element.find("byId", elementId).first();
    	element.update();
    	element.save();
    	showList(element.list.id);
    }
    
    public static void deleteElement(Long elementId){
    	Element element = Element.find("byId", elementId).first();
    	Long listId = element.list.id;
    	element.delete();
    	showList(listId);
    }
    
    public static void addElement(Long listId, String label){    	
    	ElementList list = ElementList.find("byId", listId).first();
    	list.addElement(label);
    	showList(listId);
    }

}