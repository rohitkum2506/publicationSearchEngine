package team1.tests.searchEngine.model;

import java.util.ArrayList;

import org.elasticsearch.client.transport.TransportClient;

import team1.searchengine.common.Query;
import team1.searchengine.common.QueryElement;
import team1.searchengine.database.ElasticSearchClient;
import team1.searchengine.ui.Operator;
import team1.tests.searchEngine.database.TestDataManager;

public class MockModelObjects extends TestDataManager {
	Query mockQuery = new Query("author");
	ArrayList<QueryElement> qeles = new ArrayList<QueryElement>();

	public ArrayList<QueryElement> getQueryElements() {
		qeles.add(new QueryElement("newton", "name",  Operator.EQ));
		qeles.add(new QueryElement("Elon Musk", "name",  Operator.NEQ));
		qeles.add(new QueryElement("Bill Gates", "name",  Operator.CONTAINS));

		return qeles;
	}

	public Query getQuery() {
		mockQuery.setQueryElements(getQueryElements());
		return mockQuery;
	}

	public Query getQuery(String contentType) {
		Query mQuery = new Query(contentType);
		mQuery.setQueryElements(getQueryElements());
		return mQuery;
	}

	public TransportClient getElasticSearchTestClient() {
		TransportClient client = null;
		try {
			client = new ElasticSearchClient().getDBClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return client;
	}
}
