package team1.searchengine.queryengine;

import team1.searchengine.common.*;
import team1.searchengine.model.*;;

/**
 * Specifies behavior for retrieving search results from the database for a given query
 * @author hrivks
 *
 */
public interface QueryEngineProvider {
	/**
	 * Retrieve search results from the database and return it as a collection
	 * of Publication entity objects
	 * @param query Query object representing the user query to execute
	 * @return result of the executed query wrapped as a PublicationEntity
	 */
	public PublicationEntityCollection executeQuery(String index, Query query, int size, int from) throws Exception;
	
	public void executeWriteQuery(String index, PublicationEntityCollection pec) throws Exception;
	
}
