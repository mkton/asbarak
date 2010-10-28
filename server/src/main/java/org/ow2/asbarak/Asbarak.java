package org.ow2.asbarak;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.BindingController;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.objectweb.fractal.api.control.LifeCycleController;
import org.ow2.asbarak.auth.session.SessionManagerService;
import org.ow2.asbarak.deployer.AsbarakDeployerService;
import org.ow2.frascati.FraSCAti;
import org.ow2.frascati.util.FrascatiException;

public class Asbarak {
	
	FraSCAti frascati;
	AsbarakDeployerService deployer;
	
	//AsbarakRegistryService asbarakRegistry;
	//ApplicationRegistryService applicationRegistry;
		
	public Asbarak(FraSCAti fraSCAti) throws FrascatiException, NoSuchInterfaceException, IllegalBindingException, IllegalLifeCycleException{
		
		this.frascati = fraSCAti;
		
		// we start the Asbarak composite
		Component asbarak = frascati.getComposite("asbarak");
		
		// we retrieve the deployer
		this.deployer = frascati.getService(asbarak, "deployer-service", AsbarakDeployerService.class);

		// and we set the deployer reference to the FraSCAti composite manager
		LifeCycleController lc = (LifeCycleController) asbarak.getFcInterface("lifecycle-controller");
		lc.stopFc();
		
		BindingController bc = (BindingController) asbarak.getFcInterface("binding-controller");
		bc.bindFc("composite-manager", frascati.getCompositeManager());
		
		lc.startFc();
		
		
		// now we manage intents that reference asbarak services
		// we bind session manager service from asbarak to auth-intent reference
		Component authIntent = frascati.getComposite("auth-intent");
		
		lc = (LifeCycleController) authIntent.getFcInterface("lifecycle-controller");
		lc.stopFc();
		
		bc = (BindingController) authIntent.getFcInterface("binding-controller");
		SessionManagerService sessionManager = frascati.getService(asbarak, "session-service", SessionManagerService.class);
		
		bc.bindFc("session-reference", sessionManager);
		
		lc.startFc();
		
	}
	
	public FraSCAti getFraSCAti(){
		return this.frascati;
	}
	
	public AsbarakDeployerService getDeployer(){
		return this.deployer;
	}
	
	/*
	public ApplicationRegistryService getApplicationRegistry(){
		return this.applicationRegistry;
	}
	*/
}
