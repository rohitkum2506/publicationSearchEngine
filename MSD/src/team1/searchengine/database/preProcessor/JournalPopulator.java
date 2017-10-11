package team1.searchengine.database.preProcessor;

import team1.searchengine.database.QueryService;
import team1.searchengine.model.Journal;
import team1.searchengine.model.JournalPublication;

import java.util.ArrayList;

public class JournalPopulator extends EntityPopulator {

	private QueryService queryService;

	public JournalPopulator(QueryService qs) {
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

	public boolean populate(String index, Journal journal) {
		if (journal != null) {
			String jsonForm = journal.getJsonForm();
			try {
				queryService.ExecuteIndexQuery(index, "journal", jsonForm);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean populate(String index, ArrayList<Journal> journals) {
		if (journals.size() != 0) {
			for (Journal j : journals) {
				return populate(index, j);
			}
		}
		return false;
	}

	public boolean populateBulk(String index, ArrayList<Journal> conferences) {
		ArrayList<String> authorStrs = new ArrayList<String>();
		for (Journal a : conferences) {
			authorStrs.add(a.getJsonForm());
		}
		if (authorStrs.size()!=0) {
			return queryService.ExecuteBulkIndexQuery(index, "journal", authorStrs);
		}
		return false;
	}
}
