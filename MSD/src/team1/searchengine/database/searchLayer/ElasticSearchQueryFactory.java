package team1.searchengine.database.searchLayer;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import team1.searchengine.common.Query;
import team1.searchengine.common.QueryElement;
import team1.searchengine.ui.Operator;

/**
 * Elastic search factory to create search queries
 * 
 * @author rohitkumar
 *
 */
public class ElasticSearchQueryFactory {

	/**
	 * Creates an elastic searchQuery and prepares a multiIndex Search
	 * 
	 * @param type
	 * @param query
	 * @param client
	 * @return
	 */
	public SearchRequestBuilder getElasticSearchQuery(String index, String type, Query query, TransportClient client,
			int size, int from) {

		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		for (QueryElement ele : query.getQueryElements()) {
			qb = prepareSearchQuery(index, type, ele, qb);
		}
		return client.prepareSearch(index).setTypes(query.getContentType()).setQuery(qb).setSize(size).setFrom(from);
	}

	/**
	 * This prepares the query depending on the type of filter applied.
	 * 
	 * @param index
	 * @param type
	 * @param ele
	 * @param qb
	 * @return
	 */
	public BoolQueryBuilder prepareSearchQuery(String index, String type, QueryElement ele, BoolQueryBuilder qb) {
		Operator operator = ele.getOperator();
		String field = ele.getField().trim();
		String value = ele.getKeyword().trim();

		// if the queryElement is of type nested then create a nested search
		// field
		if (ele.getNested()) {
			field = getFieldName(ele);
		}
		if (operator == Operator.GT) {
			return qb.must(new RangeQueryBuilder(field).gte(value));
		} else if (operator == Operator.LT) {
			return qb.must(new RangeQueryBuilder(field).lte(value));
		} else if (operator == Operator.EQ) {
			return qb.must(QueryBuilders.matchPhraseQuery(field, value));
		} else if (operator == Operator.NEQ) {
			return qb.mustNot(QueryBuilders.matchPhraseQuery(field, value));
		} else if (operator == Operator.CONTAINS) {
			return qb.must(new MatchQueryBuilder(field, value));
		} else if (operator == Operator.NOTCONTAINS) {
			return qb.mustNot(QueryBuilders.matchQuery(field, value));
		}

		return qb.must(QueryBuilders.matchQuery(field, value));
	}

	public String getFieldName(QueryElement ele) {
		String field = ele.getField().trim();
		if (field.equals("journal")) 
			return field + ".title";
		if (field.equals("conference")) {
			return field + ".key";
		}
		return field+".name";
	}
}
