package team1.searchengine.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.EntityPopulator;
import team1.searchengine.database.preProcessor.JournalPublicationPopulator;

/**
 * Models a publication that is published in a Journal
 * 
 * @author hrivks
 *
 */
public class JournalPublication extends GenericPublication {
	public int volume;
	public Journal journal;

	public int getVolume() {
		return volume;
	}

	public Journal getJournal() {
		return this.journal;
	}

	@Override
	public String getJsonForm() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().enableComplexMapKeySerialization()
				.serializeNulls().create();

		String json = gson.toJson(this, new TypeToken<JournalPublication>() {
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
		return new JournalPublicationPopulator(qs);
	}

	public static JournalPublication createObjFromString(String jsonInString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonInString, JournalPublication.class);
	}
}
