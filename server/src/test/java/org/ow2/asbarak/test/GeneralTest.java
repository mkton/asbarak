package org.ow2.asbarak.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.control.ContentController;
import org.ow2.asbarak.Asbarak;
import org.ow2.asbarak.test.ComponentTestService;
import org.ow2.frascati.FraSCAti;

public class GeneralTest {

	Asbarak asbarak;

	public GeneralTest() {
		try {
			
			this.asbarak = new Asbarak(FraSCAti.newFraSCAti());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeploy() {

		try {
			// we test the deployment of a composite
			Component testComp = this.asbarak.getDeployer().deploy("composite-test");
			
			// we assert that it has been plug to the os-command
			ComponentTestService test = asbarak.getFraSCAti().getService(testComp, "component-test-service", ComponentTestService.class);
			
			assertTrue(test.test()!= null);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
