package team1.searchengine.database;

import team1.searchengine.model.PublicationEntityCollection;
import team1.searchengine.common.Query;

/**
 * Acts a layer between the database and the QueryEngine. All Database queries must be 
 * executed via this interface.
 * 
 * The class implementing this interface will have access to the JDBC connection object 
 * and will execute the SQL statements using that
 * @author rohitkumar
 *
 */

public interface DatabaseServiciable {
	/**
	 * Method to execute a query based on SQL query provided in string format.
	 * @param query
	 * @return QueryData
	 */
	void ExecuteWriteQuery(String index, PublicationEntityCollection entityCollection);

	PublicationEntityCollection ExecuteSearchQuery(String index, Query query, int size, int from);
}