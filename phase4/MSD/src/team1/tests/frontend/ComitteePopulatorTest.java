package team1.tests.frontend;

import java.util.HashMap;

import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.ComitteePopulator;
import team1.searchengine.database.preProcessor.ConferencePublicationPopulator;
import team1.searchengine.frontend.CommitteeParser;
import team1.searchengine.model.ConferencePublication;

public class ComitteePopulatorTest extends TestCase{
	
	ComitteePopulator cp;
	
	@Override
	protected void setUp() throws Exception {
	}
	
	@Test
	public void testPopulateCommitteeTest(){

		ComitteePopulator.populate("committeesTest");
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("G", "test");
		ComitteePopulator.committeeData.put("G", "test");
		boolean res = ComitteePopulator.Match("test", "G");
		assertTrue(res);
	}
}
