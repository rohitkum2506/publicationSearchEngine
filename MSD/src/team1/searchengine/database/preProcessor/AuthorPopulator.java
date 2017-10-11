package team1.searchengine.database.preProcessor;

import java.util.ArrayList;

import team1.searchengine.database.QueryService;
import team1.searchengine.model.Author;

public class AuthorPopulator extends EntityPopulator {

	private QueryService queryService;

	public AuthorPopulator(QueryService qs) {
		try {
			queryService = qs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean populate(String indexName, Author author) {
		if (author != null) {
			String jsonForm = author.getJsonForm();
			try {
				queryService.ExecuteIndexQuery(indexName, "author", jsonForm);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean populate(String index, ArrayList<Author> authors) {
		if (authors.size() != 0) {
			for (Author a : authors) {
				return populate(index, a);
			}
		}
		return false;
	}

	public Boolean populateBulk(String index, ArrayList<Author> authors) {
		ArrayList<String> authorStrs = new ArrayList<String>();
		for (Author a : authors) {
			authorStrs.add(a.getJsonForm());
		}
		if (authorStrs.size() != 0) {
			return queryService.ExecuteBulkIndexQuery(index, "author", authorStrs);
		}
		return false;
	}

	public QueryService getQueryServcie() {
		return queryService;
	}
}
