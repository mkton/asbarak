package org.ow2.asbarak.registry;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.BindingController;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.objectweb.fractal.api.control.LifeCycleController;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.os.CommandProcessorService;

@Scope("COMPOSITE")
public class AsbarakRegistryImpl implements AsbarakRegistryService {

	@Reference(name="os-command-reference")
	CommandProcessorService command;
	
	public void linkWithAsbarakModules(Component component) throws NoSuchInterfaceException, IllegalBindingException, IllegalLifeCycleException {
		
		// we first stop the component
		LifeCycleController lc = 
			(LifeCycleController) component.getFcInterface("lifecycle-controller");
		lc.stopFc();
		
		
		BindingController bc = 
			(BindingController) component.getFcInterface("binding-controller");
		bc.bindFc("os-command-reference", this.command);
		
		// then we start it again
		lc.startFc();
	}

}
