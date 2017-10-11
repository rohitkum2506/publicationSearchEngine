package team1.searchengine.exceptions;

/**
 * Exception for invalid query expressions.
 * @author rohitkumar
 *
 */
public class InvalidQueryException extends Exception {

	private static final long serialVersionUID = -588986289876263657L;
	public String type = "";
	public String msg;
	
	public InvalidQueryException(String mesg) {
		this.msg = mesg;
	}

	public String toString() {
		return ("Exception Message for type: " + type + ":" + msg);
	}
}
