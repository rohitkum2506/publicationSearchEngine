package team1.searchengine.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.ConferencePopulator;

/**
 * Models a conference that contains a collection of Conference Publications
 * 
 * @author hrivks
 *
 */
public class Conference extends GenericProceeding {
	public ArrayList<ConferencePublication> publications;

	public Conference() {
	}

	@Override
	public ArrayList<ConferencePublication> getPublications() {
		return this.publications;
	}

	public void setPublications(ArrayList<ConferencePublication> publications) {
		this.publications = publications;
	}

	public Conference(ArrayList<ConferencePublication> publications) {
		super();
		this.publications = publications;
	}

	/**
	 * get the populator for the type
	 */
	@Override
	public ConferencePopulator getPopulator() {
		QueryService qs = null;
		try {
			qs = new QueryService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ConferencePopulator(qs);
	}

	/**
	 * Convert to Json object from object
	 */
	@Override
	public String getJsonForm() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().enableComplexMapKeySerialization()
				.serializeNulls().create();

		String json = gson.toJson(this, new TypeToken<Conference>() {
		}.getType());
		return json;
	}

	/**
	 * Convert to object from the Json form
	 */
	public static Conference createObjFromString(String jsonInString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonInString, Conference.class);
	}

}
