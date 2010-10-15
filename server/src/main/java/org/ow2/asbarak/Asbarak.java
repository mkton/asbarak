package org.ow2.asbarak;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
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
		
	public Asbarak() throws FrascatiException, NoSuchInterfaceException{
		
		// we start FraSCAti
		this.frascati = FraSCAti.newFraSCAti();
		
		// we start the Asbarak composite
		Component asbarak = frascati.getComposite("src/main/resources/asbarak");
		
		// we retrieve the deployer
		this.deployer = frascati.getService(asbarak, "deployer-service", AsbarakDeployerService.class);
		
		
		//asbarak.getFcInterface(ContentController)
		/*Object[] objs = asbarak.getFcInterfaces();
		for (Object o : objs){
			System.out.println(o.getClass().getSimpleName());
		}
		ContentController cc = (ContentController) asbarak.getFcInterface("content-controller");
		for (Component c : cc.getFcSubComponents()){
			NameController nc = (NameController) c.getFcInterface("name-controller");
			if (nc.getFcName().equals("deployer"))
				
		}*/
		
		//ContentController cc;
		//cc.
		
		// and we set the deployer reference to FraSCAti
		this.deployer.setCompositeManager(frascati.getCompositeManager());
		
		//asbarak.getFcInterface("");
		// ...
		
	/*	
		// now we retrieve the asbarak registry
		this.asbarakRegistry = frascati.getService(asbarak, "asbarak-registry-service", AsbarakRegistryService.class);
		
		// and the application registry
		this.applicationRegistry = frascati.getService(asbarak, "application-registry-service", ApplicationRegistryService.class);
		*/
		// now the Asbarak platform has been started and is now able to deploy 
		// applications
		
		
	}
	
	public FraSCAti getFraSCAti(){
		return this.frascati;
	}
	
	public AsbarakDeployerService getDeployer(){
		return this.deployer;
	}
	/*
	public AsbarakRegistryService getAsbarakRegistry(){
		return this.asbarakRegistry;
	}
	
	public ApplicationRegistryService getApplicationRegistry(){
		return this.applicationRegistry;
	}*/
}
