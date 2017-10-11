package team1.tests.searchEngine.database;

import java.util.ArrayList;

import org.elasticsearch.action.index.IndexResponse;

import team1.searchengine.common.Query;
import team1.searchengine.database.QueryService;
import team1.searchengine.model.Author;
import team1.searchengine.model.Conference;
import team1.searchengine.model.ConferencePublication;
import team1.searchengine.model.PublicationEntityCollection;

public class TestDataManager {

	QueryService qs = null;
	Author author;
	Author author1;
	
	public TestDataManager() {
		try {
			qs = new QueryService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Author getAuthor() {
		author = new Author("Clint Eastwood", "Good bad Ugly", "Kill everyone with a puny gun.", true, true);
		return author;
		
	}
	
	public ArrayList<Author> getAuthors() {
		author = new Author("Clint Eastwood", "Good bad Ugly", "Kill everyone with a puny gun.", true, true);
		author1 = new Author("jamie fox", "Iron fist", "work out like a king", true, true);
		ArrayList<Author> ars = new ArrayList<Author>();
		ars.add(author);
		ars.add(author1);
		return ars;	
	}

	public void deleteIndex(String indexName) {
		qs.deleteIndex(indexName);
	}

	public IndexResponse createIndex(String index, String type, String jsonData) {
		return qs.ExecuteIndexQuery(index, type, jsonData);
	}

	public PublicationEntityCollection proxyExecuteSearchQuery(String index, Query q, int size, int from) {
		return qs.ExecuteSearchQuery(index, q, size, from);
	}

	public Conference getConference() {
		Conference c = new Conference();
		c.setTitle("proceedings in the state");
		return c;
	}

	public ConferencePublication getConferencePublication() {
		ConferencePublication cPub = new ConferencePublication();
		cPub.setBookTitle("testBook");
		return cPub;
	}
}
