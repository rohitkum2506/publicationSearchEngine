package team1.searchengine.database;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;

import team1.searchengine.common.Query;

import team1.searchengine.common.QueryData;
import team1.searchengine.common.QueryElement;
import team1.searchengine.database.preProcessor.DatabasePopulator;
import team1.searchengine.database.searchLayer.ElasticSearchQueryFactory;
import team1.searchengine.logging.Logger;
import team1.searchengine.model.*;

/**
 * This class implements the interfaces used by frontend to perform search and
 * 
 * @author rohitkumar
 *
 */
public class QueryService implements DatabaseServiciable {

	private ElasticSearchClient EsClient;
	private TransportClient client;
	ElasticSearchQueryFactory esf;
	PublicationEntityCollection pebc = new PublicationEntityCollection();
	DatabasePopulator databasePopulator;
	
	ArrayList<Author> authors = new ArrayList<Author>();
	ArrayList<Journal> journals = new ArrayList<Journal>();
	ArrayList<Conference> conferences = new ArrayList<Conference>();
	ArrayList<JournalPublication> jPublications = new ArrayList<JournalPublication>();
	ArrayList<ConferencePublication> cPublications = new ArrayList<ConferencePublication>();

	public QueryService() throws Exception {
		EsClient = new ElasticSearchClient();
		client = EsClient.getDBClient();
		esf = new ElasticSearchQueryFactory();
		databasePopulator = new DatabasePopulator(this);
	}

	public ElasticSearchClient getElasticSearchClient() {
		return EsClient;
	}

	public TransportClient getclient() {
		return client;
	}

	@Override
	public void ExecuteWriteQuery(String index, PublicationEntityCollection entityCollection) {
		databasePopulator.PrePopulatorDatabase(index, entityCollection);
	}

	/**
	 * common method for running the searchbuilder and getting the response in
	 * required publicationEntitCollection format
	 * 
	 * @param qb
	 * @param type
	 * @return
	 */
	private PublicationEntityCollection ExecuteAndCreateResponse(SearchRequestBuilder qb, String type) {
		SearchResponse response = qb.get();

		if (type.equals("author")) {
			authors = getAuthors(response, authors);
		} else if (type.equals("journal")) {
			journals = getJournals(response, journals);
		} else if (type.equals("conference")) {
			conferences = getConferences(response, conferences);
		} else if (type.equals("JournalPublication")) {
			jPublications = getPublications(response, jPublications);
		} else if (type.equals("conferencePublication")) {
			cPublications = getConference(response, cPublications);
		}

		pebc.setAuthors(authors);
		pebc.setJournals(journals);
		pebc.setConferences(conferences);
		pebc.setPublications(jPublications);
		pebc.setConferencePublication(cPublications);
		return pebc;
	}

	/**
	 * Orchestrates the creation and execution of the search Query
	 * 
	 * @param: Query
	 * @return
	 */
	@Override
	public PublicationEntityCollection ExecuteSearchQuery(String index, Query query, int size, int from) {
		PublicationEntityCollection pebc = new PublicationEntityCollection();

		String type = query.getContentType();

		ArrayList<QueryElement> allQes = query.getQueryElements();

		if (allQes != null && allQes.size() != 0) {
			query.setQueryElements(allQes);
			SearchRequestBuilder queryBuilder = esf.getElasticSearchQuery(index, type, query, client, size, from);
			pebc = ExecuteAndCreateResponse(queryBuilder, type);
		}
		// EsClient.closeClient();
		return pebc;
	}

	/**
	 * gets all the ConferencePublication from the search response
	 * 
	 * @param multiRAuthor
	 * @param authors
	 * @return
	 */
	private ArrayList<ConferencePublication> getConference(SearchResponse multiRAuthor,
			ArrayList<ConferencePublication> cPublications) {
		SearchHits sr = multiRAuthor.getHits();
		if (sr != null) {
			for (SearchHit hit : sr.getHits()) {
				if (hit != null) {
					cPublications.add(ConferencePublication.createObjFromString(hit.getSourceAsString()));
				}
			}
		}
		return cPublications;
	}

	/**
	 * gets all the authors from the search response
	 * @param multiRAuthor
	 * @param authors
	 * @return
	 */
	private ArrayList<Author> getAuthors(SearchResponse multiRAuthor, ArrayList<Author> authors) {
		SearchHits sr = multiRAuthor.getHits();
		if (sr != null) {
			for (SearchHit hit : sr.getHits()) {
				if (hit != null) {
					authors.add(Author.createObjFromString(hit.getSourceAsString()));
				}
			}
		}
		return authors;
	}

	/**
	 * gets all the journals from the search response
	 * 
	 * @param multiRAuthor
	 * @param journals
	 * @return
	 */
	private ArrayList<Journal> getJournals(SearchResponse multiRAuthor, ArrayList<Journal> journals) {
		SearchHits sr = multiRAuthor.getHits();
		if (sr != null) {
			for (SearchHit hit : sr.getHits()) {
				if (hit != null) {
					journals.add(Journal.createObjFromString(hit.getSourceAsString()));
				}
			}
		}
		return journals;
	}

	/**
	 * gets all the conferences from the search response
	 * 
	 * @param multiRAuthor
	 * @param journals
	 * @return
	 */
	private ArrayList<Conference> getConferences(SearchResponse multiRAuthor, ArrayList<Conference> journals) {
		SearchHits sr = multiRAuthor.getHits();
		if (sr != null) {
			for (SearchHit hit : sr.getHits()) {
				if (hit != null) {
					journals.add(Conference.createObjFromString(hit.getSourceAsString()));
				}
			}
		}
		return journals;
	}

	/**
	 * gets all the JournalPublication from the search response
	 * 
	 * @param multiRAuthor
	 * @param publications
	 * @return
	 */
	private ArrayList<JournalPublication> getPublications(SearchResponse multiRAuthor,
			ArrayList<JournalPublication> publications) {
		SearchHits sr = multiRAuthor.getHits();
		if (sr != null) {
			for (SearchHit hit : sr.getHits()) {
				if (hit != null) {
					publications.add(JournalPublication.createObjFromString(hit.getSourceAsString()));
				}
			}
		}
		return publications;
	}

	/**
	 * Use this to insert Data in json format to Elastic search
	 * 
	 * @param index
	 * @param type
	 * @param jsonData
	 */
	public IndexResponse ExecuteIndexQuery(String index, String type, String jsonData) {
		IndexResponse r = client.prepareIndex(index, type).setSource(jsonData).get();
		String i = r.getIndex();
		String t = r.getType();
		return r;
	}
	
	public Boolean ExecuteBulkIndexQuery(String index, String type, ArrayList<String> entities) {
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (String s : entities) {
			bulkRequest.add(client.prepareIndex(index, type).setSource(s));
		}
		
		BulkResponse r = bulkRequest.get();
		if (r.hasFailures()) {
			Logger.log("Error while parsing the author data : ", "ERROR");
			return false;
		}
		return true;
	}

	/**
	 * utility to delete the whole index based on the type and ID of the index.
	 * Should be used with caution
	 * 
	 * @param indexName
	 * @param type
	 * @param id
	 */
	public void deleteItemFromIndex(String indexName, String type, String id) {
		client.prepareDelete(indexName, type, id).get();
	}

	/**
	 * utility to delete the whole Index, based on just the indexName Use with
	 * extreme caution
	 * 
	 * @param indexName
	 */
	public void deleteIndex(String indexName) {
		client.prepareDelete().setIndex(indexName).execute();
	}
}
