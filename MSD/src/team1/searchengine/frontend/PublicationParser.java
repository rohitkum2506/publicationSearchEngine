package team1.searchengine.frontend;

/**
 * Specifies behavior for parsing a publication data source
 * 
 * @author hrivks
 *
 */
public interface PublicationParser {
	/**
	 * Parses the data source and returns the representation as a RawRecordCollection
	 * @param path path to the data source file
	 * @return representation of the data source as RawRecordCollection
	 */
	public RawRecordCollection parse(String path);

}
