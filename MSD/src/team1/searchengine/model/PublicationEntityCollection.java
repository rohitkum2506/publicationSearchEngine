/**
 * 
 */
package team1.searchengine.model;

import java.util.ArrayList;

/**
 * @author hrivks Collection of PublicationEntity objects of different type
 */
public class PublicationEntityCollection {
	public String source;
	public ArrayList<Author> authors;
	public ArrayList<JournalPublication> journalPublications;
	public ArrayList<ConferencePublication> conferencePublications;
	public ArrayList<Journal> journals;
	public ArrayList<Conference> conferences;

	public PublicationEntityCollection() {
		authors = new ArrayList<>();
		journalPublications = new ArrayList<>();
		conferencePublications = new ArrayList<>();
		journals = new ArrayList<>();
		conferences = new ArrayList<>();
	}

	public ArrayList<Author> getAuthors() {
		return authors;		
	}
	
	public void setAuthors(ArrayList<Author> a) {
		authors = a;		
	}
	
	public void setPublications(ArrayList<JournalPublication> p) {
		journalPublications = p;		
	}
	
	public void setJournalPublication(ArrayList<JournalPublication> p){
		this.journalPublications = p;
	}
	
	public ArrayList<JournalPublication> getJournalPublications() {
		return journalPublications;		
	}
	
	public ArrayList<ConferencePublication> getConferencePublications() {
		return conferencePublications;		
	}
	
	public void setConferencePublication (ArrayList<ConferencePublication> c){
		this.conferencePublications =c;
	}
	
	public ArrayList<Journal> getJournals() {
		return journals;		
	}
	
	public ArrayList<Conference> getConferences() {
		return conferences;		
	}
	
	public void setJournals(ArrayList<Journal> journals){
		this.journals = journals;
	}
	
	public void setConferences(ArrayList<Conference> conferences){
		this.conferences = conferences;
	}
}
