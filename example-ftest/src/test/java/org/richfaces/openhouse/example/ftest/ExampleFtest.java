package org.richfaces.openhouse.example.ftest;

import static org.jboss.arquillian.ajocado.locator.LocatorFactory.jq;
import static org.jboss.arquillian.ajocado.Graphene.guardXhr;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.ajocado.dom.Event;
import org.jboss.arquillian.ajocado.framework.GrapheneSelenium;
import org.jboss.arquillian.ajocado.locator.JQueryLocator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ExampleFtest {

	private JQueryLocator input = jq("input#nameInput");
	private JQueryLocator output = jq("#output");

	private String EXPECTED_OUTPUT = "Red Hat OpenHouse 2012";

	@Deployment(testable = false)
	public static WebArchive createTestArchive() {

		WebArchive war = ShrinkWrap.createFromZipFile(WebArchive.class,
				new File("target/example.war"));
		return war;
	}

	@Drone
	protected GrapheneSelenium selenium;

	@Before
	public void loadTestPage() {

		URL url = null;
		try {
			url = new URL("http://localhost:8080/example");
		} catch (MalformedURLException e) {
			// this one is correct for sure
		}

		selenium.open(url);
	}

	@Test
	public void testFoo() {

		selenium.type(input, EXPECTED_OUTPUT);
		guardXhr(selenium).fireEvent(input, Event.KEYUP);
		
		String actualOutString = selenium.getText(output);
		
		assertNotNull("The output should not be null", actualOutString);
		assertTrue("The output is different!", actualOutString.contains(EXPECTED_OUTPUT));
	}
}
