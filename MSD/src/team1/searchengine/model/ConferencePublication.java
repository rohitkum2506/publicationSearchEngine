package team1.searchengine.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.ConferencePublicationPopulator;
import team1.searchengine.database.preProcessor.EntityPopulator;

/**
 * Models an publication that is published in a conference
 * 
 * @author hrivks
 *
 */
public class ConferencePublication extends GenericPublication {

	public String bookTitle;
	public String crossRef;
	public Conference conference;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getCrossRef() {
		return crossRef;
	}

	public void setCrossRef(String crossRef) {
		this.crossRef = crossRef;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	/**
	 * return the Json format of the conferencePublication Object
	 */
	@Override
	public String getJsonForm() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().enableComplexMapKeySerialization()
				.serializeNulls().create();

		String json = gson.toJson(this, new TypeToken<ConferencePublication>() {
		}.getType());
		return json;
	}

	@Override
	public EntityPopulator getPopulator() {
		QueryService qs = null;
		try {
			qs = new QueryService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ConferencePublicationPopulator(qs);
	}

	public static ConferencePublication createObjFromString(String sourceAsString) {
		Gson gson = new Gson();
		return gson.fromJson(sourceAsString, ConferencePublication.class);
	}
}
