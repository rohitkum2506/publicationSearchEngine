package team1.tests.searchEngine.model;

import static org.junit.Assert.*;
import org.junit.Test;

import team1.searchengine.model.ConferencePublication;
import team1.searchengine.model.Conference;

public class ConferencePublicationTest {
	
	@Test
	public void testConfPub() {
		ConferencePublication cb = new ConferencePublication();
		Conference conference1 = new Conference();
		cb.setBookTitle("pdp");
		cb.setConference(conference1);
		cb.setCrossRef("conf/nips/1993");
		assertEquals(cb.getBookTitle(), "pdp");
		assertEquals(cb.getCrossRef(), "conf/nips/1993");
		assertEquals(cb.getConference(), conference1);
	}
}
