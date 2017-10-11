package team1.searchengine.frontend;

import java.util.*;

/**
 * Defines a collection of RawRecord
 * 
 * @author hrivks
 *
 */
public class RawRecordCollection {
	public ArrayList<RawRecord> records;

	public RawRecordCollection() {
		this.records = new ArrayList<>();
	}

	public RawRecordCollection(ArrayList<RawRecord> records) {
		if (records != null)
			this.records = records;
	}
}
