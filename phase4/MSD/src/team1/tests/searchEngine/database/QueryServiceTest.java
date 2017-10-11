package team1.tests.searchEngine.database;

import java.util.ArrayList;

import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.common.Query;
import team1.searchengine.common.QueryElement;
import team1.searchengine.database.QueryService;
import team1.searchengine.model.Author;
import team1.searchengine.model.Conference;
import team1.searchengine.model.ConferencePublication;
import team1.searchengine.model.Journal;
import team1.searchengine.model.PublicationEntityCollection;
import team1.searchengine.ui.Operator;

public class QueryServiceTest extends TestCase {

	QueryService queryService;
	TestDataManager manager = new TestDataManager();
	IndexResponse ir;
	IndexResponse ir1;
	Journal journal;

	@Override
	public void setUp() {
		try {
			queryService = new QueryService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Author author = new Author("Bacchan", "Madhushala", "Written in the fond memory of", true, true);
		Author author1 = new Author("Jane", "Gone with the wind.", "Dedicated to my friends and family.", true, true);

		ArrayList<Author> authors = new ArrayList<Author>();
		authors.add(author);

		journal = new Journal(null, "Poems", authors, 3, 1993, "ppdsef", "MyPoems favorite", "PBH",
				"www.cloudComputing.com", "New source", "computing in cloud", "serious URL", null);
		
		Journal j1  = new Journal(null, "Cloud Computing", authors, 3, 1234, "test", "Cloud books", "really",
				"www.neu.com", "best source", "computing", "serious URL", null);
		
		manager.deleteIndex("test_dblp");
		ir = manager.createIndex("test_dblp", "journal", journal.getJsonForm());
		manager.createIndex("test_dblp", "journal", j1.getJsonForm());
		ir1 = queryService.ExecuteIndexQuery("test_dblp", "author", author1.getJsonForm());
		
	}

	@Test
	public void testExecuteIndexQuery() {

		// QueryData qd = queryService.ExecuteReadQuery(new Query(""));
		assertNotNull(ir);
		assertEquals("test_dblp", ir.getIndex());
		assertEquals("Object should be equal. They represent querydata", null, null);
	}
	
	@Test
	public void testExecuteIndexQueryAuthor() {
		assertNotNull(ir);
		assertEquals("test_dblp", ir.getIndex());
		assertEquals("Object should be equal. They represent querydata", null, null);
	}
	
	@Test
	public void testExecuteSearchQuery() {
		Query q = new Query("author");
		QueryElement qe = new QueryElement("Jane", "name", Operator.EQ);
		ArrayList<QueryElement> qes = new ArrayList<QueryElement>();
		qes.add(qe);
		q.setQueryElements(qes);
		PublicationEntityCollection pebc = manager.proxyExecuteSearchQuery("test_dblp", q,10,0);

		assertTrue(pebc.getAuthors().size() != 0);
		assertNotNull(pebc);

		q = new Query("author");
		qe = new QueryElement("Gone", "alias", Operator.CONTAINS);
		qes = new ArrayList<QueryElement>();
		qes.add(qe);
		q.setQueryElements(qes);

		PublicationEntityCollection pebc1 = queryService.ExecuteSearchQuery("test_dblp", q, 10, 0);
		
		assertTrue(pebc1.getAuthors().size() >1);
		assertEquals(pebc1.getAuthors().get(1).getName(), "Jane");
		assertNotNull(pebc1);
	}
	
	
	@Test
	public void testExecuteSearchJournalQuery() {
		Query q = new Query("journal");
		QueryElement qe = new QueryElement("Cloud computing", "title", Operator.EQ);
		ArrayList<QueryElement> qes = new ArrayList<QueryElement>();
		qes.add(qe);
		q.setQueryElements(qes);
		PublicationEntityCollection pebc = manager.proxyExecuteSearchQuery("test_dblp", q, 10, 0);

		assertNotNull(pebc);
	}
	
	@Test
	public void testExecuteSearchConferenceQuery() {
		Query q = new Query("conference");
		QueryElement qe = new QueryElement("Cloud computing", "title", Operator.EQ);
		ArrayList<QueryElement> qes = new ArrayList<QueryElement>();
		qes.add(qe);
		q.setQueryElements(qes);
		PublicationEntityCollection pebc = manager.proxyExecuteSearchQuery("test_dblp", q, 10, 0);

		assertNotNull(pebc);

		assertNotNull(pebc.getConferences());
		assertTrue(pebc.getConferences().size() == 0);
	}
	
	@Test
	public void testExecuteSearchQueryForNested(){
		manager.deleteIndex("test_dblp");
		ir = manager.createIndex("test_dblp", "conference", journal.getJsonForm());
		
		Query q = new Query("conference");
		QueryElement qe = new QueryElement("Bacchan", "editors", Operator.EQ, true);
		ArrayList<QueryElement> qes = new ArrayList<QueryElement>();
		qes.add(qe);
		q.setQueryElements(qes);
		PublicationEntityCollection pebc = manager.proxyExecuteSearchQuery("test_dblp", q, 10, 0);

		assertNotNull(pebc);

		assertNotNull(pebc.getConferences());
		assertTrue(pebc.getConferences().size() > 0);
	}
	
	@Test
	public void testExecuteSearchQueryForNestedConferencePublication(){
		manager.deleteIndex("test_dblp");
		ConferencePublication cpub =  manager.getConferencePublication();
		Conference c = manager.getConference();
		c.setKey("reference");
		cpub.setConference(c);
		
		ir = manager.createIndex("test_dblp", "conferencePublication", cpub.getJsonForm());
		
		Query q = new Query("conferencePublication");
		QueryElement qe = new QueryElement("reference", "conference", Operator.EQ, true);
		ArrayList<QueryElement> qes = new ArrayList<QueryElement>();
		qes.add(qe);
		q.setQueryElements(qes);
		PublicationEntityCollection pebc = manager.proxyExecuteSearchQuery("test_dblp", q, 10, 0);

		assertNotNull(pebc);

		assertNotNull(pebc.getConferencePublications());
		assertTrue(pebc.getConferencePublications().size() > 0);
	}
	
}
