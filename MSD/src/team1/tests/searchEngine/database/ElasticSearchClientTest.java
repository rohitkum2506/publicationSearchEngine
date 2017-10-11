package team1.tests.searchEngine.database;

import static org.junit.Assert.*;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import team1.searchengine.database.ElasticSearchClient;

public class ElasticSearchClientTest {

	ElasticSearchClient ESClient;

	@Test
	public void getClientTest() throws Exception {
		ESClient = new ElasticSearchClient();
		TransportClient client = ESClient.getDBClient();
		
		client.close();
		assertNotNull(ESClient.getClient());
	}

	@Test
	public void queryTest() {
		try {
			ESClient = new ElasticSearchClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonObject = "{\"age\":10,\"dateOfBirth\":1471466076564,\"fullName\":\"John Doe\"}";
		TransportClient client = ESClient.getDBClient();
		IndexResponse response = client.prepareIndex("test_people", "doe").setSource(jsonObject).get();
		SearchResponse allHits = client.prepareSearch("test_people").setQuery(QueryBuilders.matchAllQuery()).execute()
				.actionGet();

		SearchResponse sr = ESClient.getClient().prepareSearch("test_people").setExplain(false).setSize(10).execute()
				.actionGet();
		client.close();
		assertNotNull(allHits);
	}
}
