package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestLogin extends BasePage {

	@Test
	public void test() throws Exception {

		stiri.stiriBtn.click();
		assertTrue(search.social.getText().equalsIgnoreCase("social"));
		search.social.click();
		System.out.println("Test completed !");
//mvn -Dmaven.test.skip=true -DskipTests=true clean install
	}

}
