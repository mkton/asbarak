package org.ow2.asbarak.deployer;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.registry.ApplicationRegistryService;
import org.ow2.asbarak.registry.AsbarakRegistryService;
import org.ow2.frascati.assembly.factory.api.CompositeManager;
import org.ow2.frascati.assembly.factory.api.ManagerException;

@Scope("COMPOSITE")
public class AsbarakDeployerImpl implements AsbarakDeployerService {

	@Reference(name="application-registry-reference")
	ApplicationRegistryService applicationRegistry;
	
	@Reference(name="asbarak-registry-reference")
	AsbarakRegistryService asbarakRegistry;
	
	private CompositeManager compositeManager;

	public void setCompositeManager(CompositeManager cm){
		this.compositeManager = cm;
	}
	
	public Component deploy(String composite) throws ManagerException, NoSuchInterfaceException, IllegalBindingException, IllegalLifeCycleException {
		Component component = this.compositeManager.getComposite(composite);
		
		this.asbarakRegistry.linkWithAsbarakModules(component);
		
		this.applicationRegistry.registerApplication(component);
		
		return component;
	}

}
