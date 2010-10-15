package org.ow2.asbarak.registry;

import java.util.HashMap;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.BindingController;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.objectweb.fractal.api.control.LifeCycleController;
import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Scope;
import org.ow2.asbarak.audio.AudioPlayerItfService;
import org.ow2.asbarak.os.CommandProcessorService;

@Scope("COMPOSITE")
public class AsbarakRegistryImpl implements AsbarakRegistryService {

	@Reference(name = "os-command-reference")
	CommandProcessorService command;

	@Reference(name = "audio-player-reference")
	AudioPlayerItfService audioPlayer;

	private HashMap<String, Object> asbarakModules;
	
	@Init
	public void setModuleMap(){
		// we map the modules according to the Asbarak dictionary
		asbarakModules = new HashMap<String, Object>();
		asbarakModules.put(AsbarakDictionary.osCommandReference, command);
		asbarakModules.put(AsbarakDictionary.audioReference, audioPlayer);
	}
	
	public void linkWithAsbarakModules(Component component)
			throws NoSuchInterfaceException, IllegalBindingException,
			IllegalLifeCycleException {
		
		// we stop the component
		LifeCycleController lc = (LifeCycleController) component
				.getFcInterface("lifecycle-controller");
		lc.stopFc();

		// now we bind the needed modules to the application
		BindingController bc = (BindingController) component
		.getFcInterface("binding-controller");	
		
		for (String reference : bc.listFc()){
			if (this.asbarakModules.containsKey(reference))
				bc.bindFc(reference, this.asbarakModules.get(reference));
		}

		// then we start it again
		lc.startFc();
	}

}
