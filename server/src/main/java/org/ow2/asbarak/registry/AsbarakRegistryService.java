package org.ow2.asbarak.registry;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;

public interface AsbarakRegistryService {

	public void linkWithAsbarakModules(Component component) throws NoSuchInterfaceException, IllegalBindingException, IllegalLifeCycleException;
	
}
