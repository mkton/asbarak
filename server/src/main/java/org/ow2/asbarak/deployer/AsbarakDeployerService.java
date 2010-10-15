package org.ow2.asbarak.deployer;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.ow2.frascati.assembly.factory.api.ManagerException;

public interface AsbarakDeployerService {
	
	public Component deploy(String composite) throws ManagerException, NoSuchInterfaceException, IllegalBindingException, IllegalLifeCycleException;

	public void undeploy(String composite) throws ManagerException, Exception;
}
