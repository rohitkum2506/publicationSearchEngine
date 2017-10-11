package team1.searchengine.ui;

import java.util.ArrayList;

public class ContentTypeAttributeInfo {
	private String name;
	private String displayName;
	private ContentTypeFormat format;
	private ArrayList<Operator> operators = new ArrayList<>();
	private boolean isQueryable = true;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDisplayName(String dispName) {
		this.displayName = dispName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setFormat(ContentTypeFormat format) {
		this.format = format;
	}

	public ContentTypeFormat getFormat() {
		return this.format;
	}

	public void setOperators(ArrayList<Operator> operators) {
		this.operators = operators;
	}

	public void addOperator(Operator operator) {
		this.operators.add(operator);
	}

	public ArrayList<Operator> getOperators() {
		return operators;
	}

	public boolean getIsQueryable() {
		return this.isQueryable;
	}

	public void setQueryable(boolean isQueryable) {
		this.isQueryable = isQueryable;
	}
}
