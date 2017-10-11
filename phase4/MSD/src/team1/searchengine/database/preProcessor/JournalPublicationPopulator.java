package team1.searchengine.database.preProcessor;

import java.util.ArrayList;

import team1.searchengine.database.QueryService;
import team1.searchengine.model.Conference;
import team1.searchengine.model.JournalPublication;

public class JournalPublicationPopulator extends EntityPopulator {
	private QueryService queryService;

	public JournalPublicationPopulator(QueryService qs) {
		try {
			queryService = qs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void populate(String indexName, JournalPublication journalP) {
		if (journalP != null) {
			String jsonForm = journalP.getJsonForm();
			queryService.ExecuteIndexQuery(indexName, "JournalPublication", jsonForm);
		}
	}

	public void populate(String index, ArrayList<JournalPublication> publications) {
		if (publications.size() != 0) {
			for (JournalPublication a : publications) {
				populate(index, a);
			}
		}
	}

	public boolean populateBulk(String index, ArrayList<JournalPublication> conferences) {
		ArrayList<String> authorStrs = new ArrayList<String>();
		for (JournalPublication a : conferences) {
			authorStrs.add(a.getJsonForm());
		}
		if (authorStrs.size() != 0) {
			return queryService.ExecuteBulkIndexQuery(index, "JournalPublication", authorStrs);
		}
		return false;
	}

	public QueryService getQueryServcie() {
		return queryService;
	}
}
