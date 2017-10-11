package team1.searchengine.ui;

public enum Operator {
	EQ ("EQUAL TO"),
	NEQ ("NOT EQUAL TO"),
	LT ("LESS THAN"),
	GT ("GREATER THAN"),
	CONTAINS ("CONTAINS"),
	NOTCONTAINS ("DOES NOT CONTAINS");
	
	private String name;
	
	Operator(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
