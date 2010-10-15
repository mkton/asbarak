package org.ow2.asbarak;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.BindingController;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.objectweb.fractal.api.control.LifeCycleController;
import org.ow2.asbarak.deployer.AsbarakDeployerService;
import org.ow2.asbarak.registry.ApplicationRegistryService;
import org.ow2.asbarak.registry.AsbarakRegistryService;
import org.ow2.frascati.FraSCAti;
import org.ow2.frascati.util.FrascatiException;

public class Asbarak {
	
	FraSCAti frascati;
	AsbarakDeployerService deployer;
	AsbarakRegistryService asbarakRegistry;
	ApplicationRegistryService applicationRegistry;
		
	public Asbarak() throws FrascatiException, NoSuchInterfaceException, IllegalBindingException, IllegalLifeCycleException{
		
		// we start FraSCAti
		this.frascati = FraSCAti.newFraSCAti();
		
		// we start the Asbarak composite
		Component asbarak = frascati.getComposite("src/main/resources/asbarak");
		
		// we retrieve the deployer
		this.deployer = frascati.getService(asbarak, "deployer-service", AsbarakDeployerService.class);
				
		// and we set the deployer reference to the FraSCAti composite manager
		LifeCycleController lc = (LifeCycleController) asbarak.getFcInterface("lifecycle-controller");
		lc.stopFc();
		
		BindingController bc = (BindingController) asbarak.getFcInterface("binding-controller");
		bc.bindFc("composite-manager", frascati.getCompositeManager());
		
		lc.startFc();
	}
	
	public FraSCAti getFraSCAti(){
		return this.frascati;
	}
	
	public AsbarakDeployerService getDeployer(){
		return this.deployer;
	}
}
