package team1.searchengine.model;

import java.util.ArrayList;

/**
 * Models the common fields of a Journal and Conference
 * @author hrivks
 *
 */
public abstract class GenericProceeding extends PublicationEntity {
	public String title;
	public ArrayList<Author> editors = new ArrayList<>();
	public int volume;
	public int year;
	public String isbn;
	public String bookTitle;
	public String publisher;
	public String url;
	public String seriesTitle;
	public String seriesUrl;
	public ArrayList<String> externalReference = new ArrayList<>();
	
	public abstract ArrayList getPublications();
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<Author> getEditors() {
		return editors;
	}
	public void setEditors(ArrayList<Author> editors) {
		this.editors = editors;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSeriesTitle() {
		return seriesTitle;
	}
	public void setSeriesTitle(String seriesTitle) {
		this.seriesTitle = seriesTitle;
	}
	public String getSeriesUrl() {
		return seriesUrl;
	}
	public void setSeriesUrl(String seriesUrl) {
		this.seriesUrl = seriesUrl;
	}
	public ArrayList<String> getExternalReference() {
		return externalReference;
	}
	public void setExternalReference(ArrayList<String> externalReference) {
		this.externalReference = externalReference;
	}
	
}



