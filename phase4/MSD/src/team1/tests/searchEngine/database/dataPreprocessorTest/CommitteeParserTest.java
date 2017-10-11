package team1.tests.searchEngine.database.dataPreprocessorTest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import team1.searchengine.frontend.CommitteeParser;

public class CommitteeParserTest {

	@Test
	public void testParsing() {
		HashMap<String, String> res = CommitteeParser.parsing("committeesTest");
		String Gauth = res.get("G");
		String Eauth = res.get("E");
		String Pauth = res.get("P");
		String Cauth = res.get("C");
		
		assertTrue(Gauth.contains("Debra J"));
		assertTrue(Pauth.contains("Michael"));
		assertTrue(Eauth.contains("Perry"));
		assertTrue(Cauth.contains("Daniel"));
	}

}
