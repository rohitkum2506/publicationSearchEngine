package team1.searchengine.ui;

public enum AuthorPosition implements java.io.Serializable {
	PROGRAMCHAIR ("PROGRAM CHAIR"),
	GENERALCHAIR ("GENERAL CHAIR"),
	CONFERENCECHAIR ("CONFERENCE CHAIR"),
	EXTERNALREVIEW ("EXTERNAL REVIEW"),
	EDITOR ("EDITOR"),
	MISC ("MISC");	
	
	private String name;
	
	AuthorPosition(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
