package team1.searchengine.common;

import java.util.ArrayList;

/**
 * A Query type defines a query containing all the required criteria(QueryElements) for a query.
 * contentType - The type of the query. Can be Publication, Author, Journal, Conference
 * @author rohitkumar
 */
public class Query {
	String contentType;
	ArrayList<QueryElement> queryElements = new ArrayList<>();
	
	public Query(String contentType){
		this.contentType = contentType;
	}
	
	public String getContentType(){
		return contentType;
	}
	
	public void setContentType(String ct){
		this.contentType = ct;
	}
	
	public ArrayList<QueryElement> getQueryElements(){
		return this.queryElements;
	}
	
	public void setQueryElements(ArrayList<QueryElement> qe){
		this.queryElements = qe;
	}
	
	public void addQueryElement(QueryElement qe){
		this.queryElements.add(qe);
	}
	
	@Override
	public String toString(){
		String qes = "";
		for (QueryElement queryElement : queryElements) {
			qes += queryElement.toString();
		}
		return contentType + qes;
	}
}
