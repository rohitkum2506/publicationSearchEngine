package team1.searchengine.database.preProcessor;

import java.util.ArrayList;

import team1.searchengine.database.QueryService;
import team1.searchengine.model.ConferencePublication;
import team1.searchengine.model.JournalPublication;

public class ConferencePublicationPopulator extends EntityPopulator {
	private QueryService queryService;

	public ConferencePublicationPopulator(QueryService qs) {
		try {
			queryService = qs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean populate(String indexName, ConferencePublication conferencePublication) {
		if (conferencePublication != null) {
			String jsonForm = conferencePublication.getJsonForm();
			try {
				queryService.ExecuteIndexQuery(indexName, "conferencePublication", jsonForm);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public boolean populate(String index, ArrayList<ConferencePublication> publications) {
		if (publications.size() != 0) {
			for (ConferencePublication a : publications) {
				return populate(index, a);
			}
		}
		return false;
	}

	/**
	 * bulk insert indexes in the ElasticSearch for COnferencePublication
	 * 
	 * @param index
	 * @param conferences
	 */
	public Boolean populateBulk(String index, ArrayList<ConferencePublication> conferences) {
		ArrayList<String> authorStrs = new ArrayList<String>();
		for (ConferencePublication a : conferences) {
			authorStrs.add(a.getJsonForm());
		}
		if (authorStrs.size() != 0) {
			return queryService.ExecuteBulkIndexQuery(index, "conferencePublication", authorStrs);

		}
		return false;
	}

	public QueryService getQueryServcie() {
		return queryService;
	}
}
