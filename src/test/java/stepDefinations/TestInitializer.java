package stepDefinations;

import io.cucumber.java.Before;
import utilities.RestAssuredExtention;

public class TestInitializer {
	@Before
	public void testSetup()
	{
		RestAssuredExtention.request=null;
		RestAssuredExtention restAssuredExt= new RestAssuredExtention();
	}
}
