package org.ow2.asbarak.deployer;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.api.control.IllegalBindingException;
import org.objectweb.fractal.api.control.IllegalLifeCycleException;
import org.ow2.frascati.assembly.factory.api.ManagerException;

public interface AsbarakDeployerService {

	@POST
	@Path("/deploy/{composite}")
	public Component deploy(@PathParam("composite") String composite) throws ManagerException, NoSuchInterfaceException, IllegalBindingException, IllegalLifeCycleException;

	@POST
	@Path("/undeploy/{composite}")
	public void undeploy(@PathParam("composite") String composite) throws ManagerException, Exception;
	
	@POST
	@Path("/add-contribution")
	public void addContribution(@QueryParam("location") String path);
}
