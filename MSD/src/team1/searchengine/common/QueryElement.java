package team1.searchengine.common;

import com.sun.javafx.css.StyleCache.Key;

import team1.searchengine.ui.Operator;

/*
 * A Query type defines a query element. Each search filter is formulated as a query element 
 */
public class QueryElement {
	String keyword;
	String field;
	Operator operator;
	Boolean isNested;
	
	public QueryElement(String kw, String f, Operator op, Boolean isNested){
		this.keyword = kw;
		this.field = f;
		this.operator = op;
		this.isNested = isNested;
	}
	
	/**
	 * overloading the constructor as boolean isNested may not always be needed, by default it is false
	 * @param kw
	 * @param f
	 * @param op
	 */
	public QueryElement(String kw, String f, Operator op){
		this.keyword = kw;
		this.field = f;
		this.operator = op;
		setIsNested(false);
	}
	
	public String getKeyword(){
		return this.keyword;
	}
	
	public void setKeyword(String kw){
		this.keyword = kw;
	}
	
	public Boolean getNested(){
		return this.isNested;
	}
	
	public void setIsNested(Boolean n){
		this.isNested = n;
	}
	
	
	public void setField(String f){
		this.field = f;
	}
	
	public String getField(){
		return field;
	}
	
	public Operator getOperator(){
		return this.operator;
	}
	
	public void setOperator(Operator op){
		this.operator  = op;
	}
	
	@Override
	public String toString(){
		return keyword +  "-" + field + "-"+ operator + "-" + isNested; 
	}
}
