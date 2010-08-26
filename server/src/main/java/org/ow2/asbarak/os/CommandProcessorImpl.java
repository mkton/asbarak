package org.ow2.frascati.demonstration.ase.os;

import java.io.IOException;

import org.osoa.sca.annotations.Scope;

@Scope("COMPOSITE")
public class CommandProcessorImpl implements CommandProcessorService {

	public Process process(String command) {
		try {
			return Runtime.getRuntime().exec(command);						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

}
