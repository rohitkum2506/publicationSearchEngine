package team1.searchengine.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.JournalPopulator;

/**
 * Models a Journal that contains a collection of Jounral Publications
 * 
 * @author hrivks
 *
 */
public class Journal extends GenericProceeding {
	public ArrayList<JournalPublication> publications;

	public Journal() {
	}

	public ArrayList<JournalPublication> getPublications() {
		return this.publications;
	}

	public void setPublications(ArrayList<JournalPublication> publications) {
		this.publications = publications;
	}

	public Journal(ArrayList<JournalPublication> jps, String title, ArrayList<Author> editors, int vol, int year,
			String isbn, String bookTitle, String publisher, String url, String source, String seriesTitle,
			String seriesUrl, ArrayList<String> externalReference) {
		publications = jps;
		this.title = title;
		this.editors = editors;
		volume = vol;
		this.year = year;
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.publisher = publisher;
		this.url = url;
		this.source = source;
		this.seriesTitle = seriesTitle;
		this.seriesUrl = seriesUrl;
		this.externalReference = externalReference;
	}

	@Override
	public String getJsonForm() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().enableComplexMapKeySerialization()
				.serializeNulls().create();

		String json = gson.toJson(this, new TypeToken<Journal>() {
		}.getType());
		return json;
	}

	@Override
	public JournalPopulator getPopulator() {
		// TODO Auto-generated method stub
		QueryService qs = null;
		try {
			qs = new QueryService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JournalPopulator(qs);
	}

	public static Journal createObjFromString(String jsonInString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonInString, Journal.class);
	}
}
