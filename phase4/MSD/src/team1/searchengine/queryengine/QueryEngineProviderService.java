package team1.searchengine.queryengine;

import team1.searchengine.common.Query;
import team1.searchengine.database.QueryService;
import team1.searchengine.exceptions.InvalidQueryException;
import team1.searchengine.model.PublicationEntityCollection;

public class QueryEngineProviderService implements QueryEngineProvider {

	private QueryService queryService;

	public QueryEngineProviderService() {
		try {
			queryService = new QueryService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PublicationEntityCollection executeQuery(String index, Query query, int size, int from) throws InvalidQueryException {
		if (query != null) {
			if (query.getQueryElements().size() == 0) {
//				throw new InvalidQueryException("Empty Query Elements");
			} else {
				return queryService.ExecuteSearchQuery(index, query, size, from);
			}
		}
		return null;
	}

	@Override
	public void executeWriteQuery(String index, PublicationEntityCollection pec) throws Exception {
		if (pec != null) {
			queryService.ExecuteWriteQuery(index, pec);
		}
	}

}
