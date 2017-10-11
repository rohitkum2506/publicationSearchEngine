package team1.tests.searchEngine.database.searchLayerTest;

import java.util.ArrayList;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.junit.Test;

import junit.framework.TestCase;
import team1.searchengine.common.Query;
import team1.searchengine.database.searchLayer.ElasticSearchQueryFactory;
import team1.tests.searchEngine.model.MockModelObjects;


public class ElasticSearchQueryFactoryTest extends TestCase {
	
	@Override
	protected void setUp() throws Exception {

	}

	@Test
	public void testGetELasticSearchQuery() {
		ElasticSearchQueryFactory esqf = new ElasticSearchQueryFactory();
		MockModelObjects mockObjects = new MockModelObjects();
		Query query = mockObjects.getQuery("author");

		SearchRequestBuilder searchBuilders = esqf.getElasticSearchQuery("test_dblp", "author", query,
				mockObjects.getElasticSearchTestClient(), 10, 0);

		assertNotNull(searchBuilders);
		assertTrue(searchBuilders != null);
	}

	@Test
	public void prepareSearchIndex() {
		ElasticSearchQueryFactory esqf = new ElasticSearchQueryFactory();
		MockModelObjects mockObjects = new MockModelObjects();
		SearchRequestBuilder srb = esqf.getElasticSearchQuery("test_dblp", "author", mockObjects.getQuery(),
				mockObjects.getElasticSearchTestClient(), 10, 0 );
		assertNotNull(srb);
	}
}
