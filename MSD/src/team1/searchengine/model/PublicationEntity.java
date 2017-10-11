package team1.searchengine.model;

import com.google.gson.Gson;
import com.google.gson.reflect.*;

import team1.searchengine.database.preProcessor.EntityPopulator;

/**
 * Models the parent of all data model types related to publication
 * 
 * @author hrivks
 *
 */
public abstract class PublicationEntity {
	public int id;
	public String elasticId;
	public String key;
	public String source;


	public String getJsonForm() {
		Gson gson = new Gson();
		
		String json = gson.toJson(this, new TypeToken<PublicationEntity>() {}.getType());
		System.out.println(json);
		return json;
	}
	
	public String getElasticId(){
		return elasticId;
	}
	
	public void setElasticId(String id){
		this.elasticId = id;
	}

	public int getId() {
		return id;
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(String k) {
		this.key = k;
	}

	public String getSource() {
		return source;
	}
	
	public void setSource(String source){
		this.source = source;
	}

	public abstract EntityPopulator getPopulator();
}
