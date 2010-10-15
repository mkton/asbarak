package org.ow2.asbarak.deployer;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.ow2.frascati.assembly.factory.api.CompositeManager;
import org.ow2.frascati.assembly.factory.api.ManagerException;

public interface AsbarakDeployerService {

	public void setCompositeManager(CompositeManager cm);
	
	public Component deploy(String composite) throws ManagerException, NoSuchInterfaceException, IllegalBindingException, IllegalLifeCycleException;
}
