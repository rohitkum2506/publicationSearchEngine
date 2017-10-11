package team1.searchengine.database.preProcessor;

import java.util.ArrayList;

import team1.searchengine.database.QueryService;
import team1.searchengine.model.Author;
import team1.searchengine.model.Conference;
import team1.searchengine.model.PublicationEntity;


public class ConferencePopulator extends EntityPopulator{
	private QueryService queryService;

	public ConferencePopulator(QueryService qs) {
		try {
			queryService = qs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public QueryService getQueryServcie() {
		return queryService;
	}

	public void populate(String IndexName, Conference conference) {
		if (conference != null) {
			String jsonForm = conference.getJsonForm();	
			queryService.ExecuteIndexQuery(IndexName, "conference", jsonForm);
		}
	}
	
	public void populate(String indexName, ArrayList<Conference> conferences) {
		if (conferences.size() != 0) {
			for (Conference conf : conferences) {
				populate(indexName, conf);
			}
		}
	}
	
	public void populateBulk(String index, ArrayList<Conference> conferences){
		ArrayList<String> authorStrs = new ArrayList<String>();
		for (Conference a : conferences) {
			authorStrs.add(a.getJsonForm());
		}
		if (authorStrs.size() != 0) {
			queryService.ExecuteBulkIndexQuery(index, "conference", authorStrs);
		}
		
	}
}
