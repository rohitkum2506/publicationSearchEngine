package team1.tests.searchEngine.database.dataPreprocessorTest;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.AuthorPopulator;
import team1.searchengine.model.Author;
import team1.searchengine.model.Journal;

public class AuthorPopulatorTest extends TestCase {
	Journal j1, j2;
	Author author;
	ArrayList<Author> authors;
	Journal journal;
	ArrayList<Journal> journals = new ArrayList<Journal>();

	@Override
	protected void setUp() throws Exception {
		author = new Author("test", "ut", "no note", true, true);
		authors = new ArrayList<Author>();
		authors.add(author);
	}

	@Test
	public void testauthorPopulatorTest() {
		AuthorPopulator ap = null;
		try {
			ap = new AuthorPopulator(new QueryService());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean res = ap.populate("test_dblp", author);
		assertTrue(res);
	}
	
	@Test
	public void testBulkAuthorPopulatorTest() {
		AuthorPopulator ap = null;
		try {
			ap = new AuthorPopulator(new QueryService());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean res = ap.populateBulk("test_dblp", authors);
		assertTrue(res);
	}
}
