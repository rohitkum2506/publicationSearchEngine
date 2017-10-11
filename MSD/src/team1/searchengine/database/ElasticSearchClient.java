package team1.searchengine.database;

import java.net.InetSocketAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import org.elasticsearch.transport.client.PreBuiltTransportClient;

import team1.searchengine.common.Query;
import team1.searchengine.configuration.EnvironmentMap;

public class ElasticSearchClient {

	public Settings settings;
	public TransportClient client;

	public ElasticSearchClient() throws Exception {
		// TransportAddress address = new
		// InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
		String elasticP = EnvironmentMap.getElasticPort();
//		InetSocketTransportAddress address = new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300));
//		54.213.204.149
		InetSocketTransportAddress address = new InetSocketTransportAddress(new InetSocketAddress("54.213.204.149", 9300));
		settings = Settings.builder().build();
		client = new PreBuiltTransportClient(settings).addTransportAddress(address);
	}

	public TransportClient getClient() {
		return client;
	}

	/**
	 * maintaining the singleton behavior of the transport client
	 * @return
	 */
	public TransportClient getDBClient() {
		if (client == null) {
			// settings = Settings.builder().put("cluster.name",
			// "Team1").put("client.transport.sniff", true).build();
			String elasticP = EnvironmentMap.getElasticPort();
			settings = Settings.builder().build();
//			InetSocketTransportAddress address = new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300));
			InetSocketTransportAddress address = new InetSocketTransportAddress(new InetSocketAddress("54.213.204.149", 9300));
			client = new PreBuiltTransportClient(settings).addTransportAddress(address);
		}
		return client;
	}

	/**
	 * elasticSearch search functionality.
	 * 
	 * @param query
	 */
	public void executeQueryClient(Query query) {
		getDBClient().prepareSearch(query.getContentType()).setTypes(query.getQueryElements().get(0).getKeyword());
		closeClient();
	}

	/**
	 * closing the ES tranport client
	 */
	public void closeClient() {
		this.client.close();
	}
}
