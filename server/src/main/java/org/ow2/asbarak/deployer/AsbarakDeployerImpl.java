package org.ow2.asbarak.deployer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
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

	@Reference(name = "application-registry-reference")
	ApplicationRegistryService applicationRegistry;

	@Reference(name = "asbarak-registry-reference")
	AsbarakRegistryService asbarakRegistry;

	@Reference(name = "composite-manager-reference")
	CompositeManager compositeManager;

	public Component deploy(String composite) throws ManagerException,
			NoSuchInterfaceException, IllegalBindingException,
			IllegalLifeCycleException {
		Component component = this.compositeManager.getComposite(composite);

		return this.deploy(component);
	}

	public Component deploy(Component component)
			throws NoSuchInterfaceException, IllegalBindingException,
			IllegalLifeCycleException {
		
		this.asbarakRegistry.linkWithAsbarakModules(component);

		this.applicationRegistry.registerApplication(component);

		return component;
	}

	public void undeploy(String compositeName) throws Exception {

		//Component application = this.applicationRegistry
		//		.getApplication(compositeName);

		compositeManager.removeComposite(compositeName);

		this.applicationRegistry.unregisterApplication(compositeName);

	}
	
	public void addContribution(String path){
		try {
			this.addContribution(new URI(path));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addContribution(URI contribution) {
		
		String localContribution;

		try {

			// if it's a distant resource, we've to download it
			if (contribution.getScheme().equals("http")) {
				String destination = System.getProperty("java.io.tmpdir")
						+ File.separator + contribution.getFragment();
				FileUtils.copyURLToFile(contribution.toURL(), new File(
						destination));
				localContribution = destination;
			} else
				localContribution = contribution.getPath().toString();
			
			Component[] components = compositeManager
					.getContribution(localContribution);

			for (Component component : components)
				deploy(component);

		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalLifeCycleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
