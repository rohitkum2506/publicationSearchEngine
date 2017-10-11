package team1.searchengine.model;

import java.util.ArrayList;

/**
 * Models the common fields of publications in a journal and conference
 * @author hrivks
 *
 */
public abstract class GenericPublication extends PublicationEntity {
	public String title;
	public ArrayList<Author> authors = new ArrayList<>();
	public int year;
	public int issueNumber;
	public String url;
	public ArrayList<String> externalReference =new ArrayList<>();
	public String meta;
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public void setIssueNumber(int issueNumber){
		this.issueNumber = issueNumber;
	}
	
	public int getIssueNumber(){
		return this.issueNumber;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setExternalReference(ArrayList<String> ee){
		this.externalReference = ee;
	}
	
	public void addExternalReference(String ee){
		this.externalReference.add(ee);
	}
	
	public ArrayList<String> getExternalReference(){
		return this.externalReference;
	}
	
	public void setAuthors(ArrayList<Author> authors){
		this.authors = authors;
	}
	
	public void addAuthor(Author a){
		this.authors.add(a);
	}
	
	public ArrayList<Author> getAuthors(){
		return this.authors;
	}
	
}
