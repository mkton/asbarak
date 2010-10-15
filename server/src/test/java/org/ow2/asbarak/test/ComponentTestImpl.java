package org.ow2.asbarak.test;

import org.osoa.sca.annotations.Reference;
import org.ow2.asbarak.os.CommandProcessorService;

public class ComponentTestImpl implements ComponentTestService {

	@Reference(name="os-command-reference")
	CommandProcessorService command;
	
	public String test() {
		Process p = command.process("ls");
		return p.getOutputStream().toString();
	}

}
