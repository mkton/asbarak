package org.ow2.asbarak.os;

import org.osoa.sca.annotations.Service;

@Service
public interface CommandProcessorService {

	/**
	 * 
	 * Execute a command and return the associated process
	 * 
	 * @param command which could be executed on this operating system 
	 * @return
	 */
	public Process process(String command);
	
}
