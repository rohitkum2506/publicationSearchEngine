package team1.searchengine.ui;

import java.util.ArrayList;

public class ContentTypeInfo {
	private ContentType name;
	private ArrayList<ContentTypeAttributeInfo> attributes = new ArrayList<>();
	
	public ContentType getName(){
		return name;
	}
	
	public void setName(ContentType name){
		this.name = name;
	}
	
	public void setAttributes(ArrayList<ContentTypeAttributeInfo> attributes){
		this.attributes = attributes;
	}
	
	public void addAttribute(ContentTypeAttributeInfo attribute){
		this.attributes.add(attribute);
	}
	
	public ArrayList<ContentTypeAttributeInfo> getAttributes(){
		return this.attributes;
	}
	
	

}
