package team1.searchengine.ui;
import team1.searchengine.common.*;

/**
 * Specifies behavior for creating search query representation and
 * communicating with the QueryEngine
 * @author hrivks
 *
 */
public interface SearchUIProvider {
	/**
	 * Build Query object that represents the search query specified
	 * by the user in the UI window
	 * @return Query object corresponding to the users input in the UI
	 */
	public Query buildSearchQuery();
	
	/**
	 * Display the results of executing the given query
	 * @param query Object representing the user query
	 */
	public void showSearchResults(Query query);

}
