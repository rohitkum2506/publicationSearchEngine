package team1.searchengine.frontend;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * Models a key value pair that represents a single information in the data
 * source
 * 
 * @author hrivks
 *
 */
public class RawRecord {
	public String key;
	public Object value;
	public ArrayList<RawAttribute> attributes;

	public RawRecord() {
		attributes = new ArrayList<>();
	}

	public RawRecord(String key, Object value, ArrayList<RawAttribute> attributes) {
		this.key = key;
		this.value = value;
		this.attributes = attributes;
	}

	/**
	 * Get the value of the attribute by name.
	 * @param name
	 * @return {@link String}
	 */
	public String getAttribute(String name) {
		return (attributes.stream().filter(p -> p.key == name).findFirst().orElse(new RawAttribute())).value;
	}
}
