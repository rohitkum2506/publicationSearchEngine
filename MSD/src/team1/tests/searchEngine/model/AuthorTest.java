package team1.tests.searchEngine.model;

import static org.junit.Assert.*;

import org.junit.Test;

import team1.searchengine.database.preProcessor.AuthorPopulator;
import team1.searchengine.database.preProcessor.JournalPopulator;
import team1.searchengine.model.Author;

public class AuthorTest {

	@Test
	public void testCreateObjFromString() {
		String AuthorString = "{\"name\": \"test\",\"alias\": \"ut\",\"note\": \"no note\",\"hasBeenEditor\": true,\"hasBeenPC\": true,\"id\": 0}";

		Author nwa = Author.createObjFromString(AuthorString);
		assertEquals(nwa.getName(), "test");
		
	}

	@Test
	public void testCreateStringFromObj() {
		String AuthorString = "{\"name\":\"test\",\"alias\":\"ut\",\"note\":\"no note\",\"hasBeenEditor\":true,\"hasBeenPC\":true,\"id\":0}";
		
		Author nwa = new Author("test", "ut", "no note", true, true);
		String res = nwa.getJsonForm();
		System.out.println("---------------------------------------------------");
		System.out.println(res);
		assertTrue(AuthorString.equals(res));
		assertEquals(nwa.getAlias(), "ut");
		
	}
	

	@Test
	public void testgetPopulatorTest(){
		Author a = new Author("test", "ut", "no note", true, true);
		AuthorPopulator ap = a.getPopulator();
		assertTrue(ap instanceof AuthorPopulator);
	}
}
