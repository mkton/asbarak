package org.ow2.frascati.demonstration.ase.deployer;

import org.osoa.sca.annotations.Service;
import org.ow2.frascati.assembly.factory.api.CompositeManager;

@Service
public interface InterfaceManagerService {

	public void deploy(String compositeName);
	
	public void undeploy(String compositeName);
	
	// hack : see Readme and Main.java in web-client
	public void setCompositeManager(CompositeManager compositeManager);
	
}
