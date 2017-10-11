package team1.searchengine.queryengine;

import team1.searchengine.common.Query;

/**
 * interface for Query builder. Specifies necessary behavior of QueryBuilder class
 * @author rohitkumar
 *
 */
public interface QueryBuilder {
	/**
	 * used to build a query based on array of params provided in string format
	 * @param args
	 * @return Query
	 */
	Query buildQuery(String[] args);
}
