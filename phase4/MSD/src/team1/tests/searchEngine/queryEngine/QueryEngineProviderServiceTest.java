package team1.tests.searchEngine.queryEngine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.TestCase;
import team1.searchengine.common.Query;
import team1.searchengine.common.QueryElement;
import team1.searchengine.database.QueryService;
import team1.searchengine.exceptions.InvalidQueryException;
import team1.searchengine.queryengine.QueryEngineProviderService;
import team1.searchengine.ui.Operator;


/**
 * Test class for the Query Class. Includes all test cases for the Query class
 * @author rohitkumar
 *
 */
public class QueryEngineProviderServiceTest extends TestCase{

	private QueryEngineProviderService qeps;
	QueryService mockQs ;
	String index = "test_dblp";
	
	
	@Override
	public void setUp(){
		qeps = new QueryEngineProviderService();
		mockQs  = Mockito.mock(QueryService.class);
	}
	
	@Test
	public void testexecuteQueryMethodNotCalled() {
		Query q = new Query("author");
		
		try {
			qeps.executeQuery(index, q, 0, 0);
		} catch (InvalidQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Mockito.verify(mockQs, Mockito.never()).ExecuteSearchQuery(index, q,10,0);
	}
	
//	@Test
//	public void testexecuteQueryMethodCalled() {
//		Query q = new Query("author");
//		ArrayList<QueryElement> arr = new ArrayList<QueryElement>();
//		arr.add(new QueryElement("Newton", "name", Operator.CONTAINS));
//		arr.add(new QueryElement("Newton", "blah", Operator.CONTAINS));
//		q.setQueryElements(arr);
//		
//		
//		try {
//			qeps.executeQuery(index, q);
//		} catch (InvalidQueryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Mockito.verify(mockQs).ExecuteSearchQuery(index, q);
//		
//	}
}
