package team1.tests.searchEngine.queryEngine;

import static org.junit.Assert.*;

import org.junit.Test;

import team1.searchengine.common.Query;
/**
 * Test class for the Query Class. Includes all test cases for the Query class
 * @author rohitkumar
 *
 */
public class QueryTest {

	@Test
	public void testQuery() {
		Query testQuery = new Query("Author");
		assertEquals("The content Type must have content type as Author", "Author" ,testQuery.getContentType());
	}
}
