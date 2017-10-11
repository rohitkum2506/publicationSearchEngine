package team1.searchengine.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.AuthorPopulator;
import team1.searchengine.database.preProcessor.ComitteePopulator;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Models a person who has authored an article
 * 
 * @author hrivks
 */
public class Author extends PublicationEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String alias;
	public String note;
	public ArrayList<String> url = new ArrayList<>();
	public boolean hasBeenEditor;
	public boolean hasBeenPC;

	// creating this only for testing Elasticsearch.
	public Author(String n, String alias, String note, boolean hasBeenPC, boolean hasBeenEditor) {
		this.name = n;
		this.alias = alias;
		this.note = note;
		this.hasBeenEditor = hasBeenEditor;
		this.hasBeenPC = hasBeenPC;
		this.url = null;
	}

	/**
	 * Convert to json form form object
	 */
	@Override
	public String getJsonForm() {
		Gson gson = new Gson();

		String json = gson.toJson(this, new TypeToken<Author>() {
		}.getType());
		return json;
	}

	public Author() {
	}

	public String getName() {
		return StringEscapeUtils.unescapeHtml4(this.name);
	}

	public String getRawName() {
		return this.name;
	}

	public String getNote() {
		return this.note;
	}

	public String getAlias() {
		return this.alias;
	}

	public ArrayList<String> geturl() {
		return this.url;
	}

	public boolean getHasBeenEditor() {
		return this.hasBeenEditor;
	}

	public boolean getHasBeenPC() {
		return this.hasBeenPC;
	}

	public boolean isProgramChair() {
		return ComitteePopulator.Match(this.name, "P");
	}

	public boolean isGeneralChair() {
		return ComitteePopulator.Match(this.name, "G");
	}

	public boolean isConferenceChair() {
		return ComitteePopulator.Match(this.name, "C");
	}

	public boolean isInExternalReviewComittee() {
		return ComitteePopulator.Match(this.name, "E");
	}

	public boolean isCommitteeMember() {
		return ComitteePopulator.Match(this.name, "M");
	}

	/**
	 * Create the populator for the entity
	 */
	@Override
	public AuthorPopulator getPopulator() {
		AuthorPopulator ap = null;
		try {
			ap = new AuthorPopulator(new QueryService());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ap;
	}

	/**
	 * Convert to object from the Json form
	 */
	public static Author createObjFromString(String jsonInString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonInString, Author.class);
	}
}
