package org.ow2.asbarak.registry;

import java.util.HashMap;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.NameController;
import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class ApplicationRegistryImpl implements ApplicationRegistryService {

	public HashMap<String, Component> applications = new HashMap<String, Component>();
	
	public void registerApplication(Component component) throws NoSuchInterfaceException {
		
		// we retrieve the component name for a key usage
		String componentName = ((NameController) component.getFcInterface("name-controller")).getFcName();
		
		applications.put(componentName, component);
		
	}

	public void unregisterApplication(String compositeName) {
		if (applications.containsKey(compositeName))
			applications.remove(compositeName);
	}

	public Component getProviderFor(Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public Component getApplication(String compositeName) throws Exception {
		
		if (applications.containsKey(compositeName))
			return applications.get(compositeName);
		else 
			//TODO create specific exception
			throw new Exception(compositeName + " does not exist");
	}

}
