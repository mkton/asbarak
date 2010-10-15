package org.ow2.asbarak.bootstrap;

import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.ow2.asbarak.Asbarak;
import org.ow2.asbarak.deployer.AsbarakDeployerService;
import org.ow2.frascati.util.FrascatiException;

public class AsbarakBootstrap {
	
	/**
	 * @param name of composite to deploy
	 * @throws IllegalLifeCycleException 
	 * @throws IllegalBindingException 
	 */
	public static void main(String[] args) throws IllegalBindingException, IllegalLifeCycleException {
		try {
			Asbarak asbarak = new Asbarak();
			
			AsbarakDeployerService deployer = asbarak.getDeployer();
			
			for (String arg : args){
				deployer.deploy(arg);
			}
			
		} catch (FrascatiException e) {
			e.printStackTrace();
		} catch (NoSuchInterfaceException e) {
			e.printStackTrace();
		}
	}

}
