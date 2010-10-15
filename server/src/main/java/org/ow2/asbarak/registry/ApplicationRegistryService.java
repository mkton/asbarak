package org.ow2.asbarak.registry;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.osoa.sca.annotations.Service;

@Service
public interface ApplicationRegistryService {
	
	public void registerApplication(Component component) throws NoSuchInterfaceException;
	
	public void unregisterApplication(String compositeName);
	
	public Component getProviderFor(Class<?> clazz);
	
}
