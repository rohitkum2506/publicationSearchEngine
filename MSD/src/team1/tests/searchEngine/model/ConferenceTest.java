package team1.tests.searchEngine.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import team1.searchengine.database.preProcessor.AuthorPopulator;
import team1.searchengine.database.preProcessor.ConferencePopulator;
import team1.searchengine.model.Author;
import team1.searchengine.model.Conference;
import team1.searchengine.model.ConferencePublication;

public class ConferenceTest {

	@Test
	public void testConference() {
		ConferencePublication cb = new ConferencePublication();
		Conference conference1 = new Conference();
		cb.setBookTitle("pdp");
		cb.setConference(conference1);
		cb.setCrossRef("conf/nips/1993");
		ArrayList<ConferencePublication> cbList = new ArrayList<ConferencePublication>();
		cbList.add(cb);
		Conference conf2 = new Conference(cbList);
		assertEquals(conf2.getPublications(), cbList);
	}
	
	
	@Test
	public void testgetPopulatorTest(){
		Conference conference1 = new Conference();
		ConferencePopulator cp = conference1.getPopulator();
		assertTrue(cp instanceof ConferencePopulator);
	}
}
