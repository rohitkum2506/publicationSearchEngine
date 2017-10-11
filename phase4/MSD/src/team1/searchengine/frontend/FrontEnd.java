/**
 * 
 */
package team1.searchengine.frontend;

import java.util.ArrayList;

import team1.searchengine.database.QueryService;
import team1.searchengine.database.preProcessor.DatabasePopulator;
import team1.searchengine.mock.MockFrontEnd;
import team1.searchengine.model.*;
import team1.searchengine.queryengine.QueryEngineProviderService;

/**
 * @author hrivks Main Front end class
 */
public class FrontEnd implements FrontEndProvider {

	@Override
	public PublicationEntityCollection convert(RawRecordCollection records, String source) {
		PublicationEntityCollection result = new PublicationEntityCollection();
		for (RawRecord thisRR : records.records) {
			try {
				if (thisRR.key == "article") {
					result.journalPublications.add(convertToJournalPublication(thisRR, source));
				} else if (thisRR.key == "inproceedings") {
					result.conferencePublications.add(convertToConferencePublication(thisRR, source));
				} else if (thisRR.key == "proceedings") {
					String key = thisRR.getAttribute("key");
					if (key != null && key.contains("journals/")) {
						result.journals.add(convertToJournal(thisRR, source));
					} else if (key != null && key.contains("conf/")) {
						result.conferences.add(convertToConference(thisRR, source));
					} else {
						throw new IllegalArgumentException("key attribute missing on root element");
					}
				} else if (thisRR.key == "author") {
					result.authors.add(convertToAuthor(thisRR, source));
				}

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return result;
	}

	/**
	 * Convert a RawRecord corresponding to "author" to a Author
	 * 
	 * @param record
	 * @param source
	 * @return {@link Author}
	 */
	public Author convertToAuthor(RawRecord record, String source) {
		Author thisAuthor = new Author();
		thisAuthor.key = record.getAttribute("key");
		for (RawRecord thisRow : ((RawRecordCollection) record.value).records) {
			if (thisRow.key == "author") {
				if (thisAuthor.name == null)
					thisAuthor.name = thisRow.value.toString();
				else
					thisAuthor.alias = thisAuthor.alias == null ? thisRow.value.toString()
							: thisAuthor.alias + " ; " + thisRow.value.toString();
			} else if (thisRow.key == "url") {
				thisAuthor.url.add(thisRow.value.toString());
			} else if (thisRow.key == "note") {
				thisAuthor.note = thisAuthor.note == null ? thisRow.value.toString()
						: thisAuthor.note + " ; " + thisRow.value.toString();
			}
		}
		return thisAuthor;
	}

	/**
	 * Convert a RawRecord corresponding to a Journal "article" to a Journal
	 * Publication
	 * 
	 * @param record
	 * @param source
	 * @return {@link JournalPublication}
	 */
	public JournalPublication convertToJournalPublication(RawRecord record, String source) {
		JournalPublication thisPub = new JournalPublication();
		thisPub.source = source;
		thisPub.key = record.getAttribute("key");

		// iterate through raw records and map values to fields
		for (RawRecord thisRow : ((RawRecordCollection) record.value).records) {
			if (thisRow.key == "title") {
				thisPub.title = thisRow.value.toString();
				thisPub.title = thisPub.title.length() > 1000 ? thisPub.title.substring(0, 999) : thisPub.title;
			} else if (thisRow.key == "year") {
				thisPub.year = Integer.parseInt(thisRow.value.toString());
			} else if (thisRow.key == "volume") {
				thisPub.volume = Integer.parseInt(thisRow.value.toString());
			} else if (thisRow.key == "journal") {
				Journal journal = new Journal();
				journal.title = thisRow.value.toString();
				thisPub.journal = journal;
			} else if (thisRow.key == "author") {
				Author thisAuthor = new Author();
				thisAuthor.name = thisRow.value.toString();
				thisPub.authors.add(thisAuthor);
			} else if (thisRow.key == "url") {
				thisPub.url = thisRow.value.toString();
			} else if (thisRow.key == "ee") {
				thisPub.externalReference.add(thisRow.value.toString());
			} else if (thisRow.key == "number") {
				thisPub.issueNumber = Integer.parseInt(thisRow.value.toString());
			}
		}

		return thisPub;
	}

	/**
	 * Convert a RawRecord corresponding to a Journal "article" to a Conference
	 * Publication
	 * 
	 * @param record
	 * @param source
	 * @return {@link ConferencePublication}
	 */
	public ConferencePublication convertToConferencePublication(RawRecord record, String source) {
		ConferencePublication thisPub = new ConferencePublication();
		thisPub.source = source;
		thisPub.key = record.getAttribute("key");

		// iterate through raw records and map values to fields
		for (RawRecord thisRow : ((RawRecordCollection) record.value).records) {
			if (thisRow.key == "title") {
				thisPub.title = thisRow.value.toString();
				thisPub.title = thisPub.title.length() > 1000 ? thisPub.title.substring(0, 999) : thisPub.title;
			} else if (thisRow.key == "year") {
				thisPub.year = Integer.parseInt(thisRow.value.toString());
			} else if (thisRow.key == "crossref") {
				thisPub.crossRef = thisRow.value.toString();
				Conference conference = new Conference();
				conference.key = thisPub.crossRef;
				thisPub.conference = conference;
			} else if (thisRow.key == "booktitle") {
				thisPub.bookTitle = thisRow.value.toString();
			} else if (thisRow.key == "author") {
				Author thisAuthor = new Author();
				thisAuthor.name = thisRow.value.toString();
				thisPub.authors.add(thisAuthor);
			} else if (thisRow.key == "url") {
				thisPub.url = thisRow.value.toString();
			} else if (thisRow.key == "ee") {
				thisPub.externalReference.add(thisRow.value.toString());
			} else if (thisRow.key == "number") {
				thisPub.issueNumber = Integer.parseInt(thisRow.value.toString());
			}
		}
		return thisPub;
	}

	/**
	 * Convert a RawRecord corresponding to a Journal "proceeding" to a Journal
	 * 
	 * @param record
	 * @param source
	 * @return {@link Journal}
	 */
	public Journal convertToJournal(RawRecord record, String source) {
		Journal thisJournal = new Journal();
		thisJournal.key = record.getAttribute("key");

		for (RawRecord thisRow : ((RawRecordCollection) record.value).records) {
			if (thisRow.key == "title") {
				thisJournal.title = thisRow.value.toString();
				thisJournal.title = thisJournal.title.length() > 1000 ? thisJournal.title.substring(0, 999)
						: thisJournal.title;
			} else if (thisRow.key == "year") {
				thisJournal.year = Integer.parseInt(thisRow.value.toString());
			} else if (thisRow.key == "volume") {
				thisJournal.volume = Integer.parseInt(thisRow.value.toString());
			} else if (thisRow.key == "editor") {
				Author thisEditor = new Author();
				thisEditor.name = thisRow.value.toString();
				thisJournal.editors.add(thisEditor);
			} else if (thisRow.key == "url") {
				thisJournal.url = thisRow.value.toString();
			} else if (thisRow.key == "booktitle") {
				thisJournal.bookTitle = thisRow.value.toString();
			} else if (thisRow.key == "ee") {
				thisJournal.externalReference.add(thisRow.value.toString());
			} else if (thisRow.key == "isbn") {
				thisJournal.isbn = thisRow.value.toString();
			} else if (thisRow.key == "publisher") {
				thisJournal.publisher = thisRow.value.toString();
			} else if (thisRow.key == "series") {
				thisJournal.seriesTitle = thisRow.value.toString();
				thisJournal.seriesUrl = thisRow.getAttribute("href");
			}
		}
		return thisJournal;
	}

	/**
	 * Convert a RawRecord corresponding to a Conference "proceeding" to a
	 * Conference
	 * 
	 * @param record
	 * @param source
	 * @return {@link Conference}
	 */
	public Conference convertToConference(RawRecord record, String source) {
		Conference thisConference = new Conference();
		thisConference.key = record.getAttribute("key");

		for (RawRecord thisRow : ((RawRecordCollection) record.value).records) {
			if (thisRow.key == "title") {
				thisConference.title = thisRow.value.toString();
				thisConference.title = thisConference.title.length() > 1000 ? thisConference.title.substring(0, 999)
						: thisConference.title;
			} else if (thisRow.key == "year") {
				thisConference.year = Integer.parseInt(thisRow.value.toString());
			} else if (thisRow.key == "volume") {
				thisConference.volume = Integer.parseInt(thisRow.value.toString());
			} else if (thisRow.key == "editor") {
				Author thisEditor = new Author();
				thisEditor.name = thisRow.value.toString();
				thisConference.editors.add(thisEditor);
			} else if (thisRow.key == "url") {
				thisConference.url = thisRow.value.toString();
			} else if (thisRow.key == "booktitle") {
				thisConference.bookTitle = thisRow.value.toString();
			} else if (thisRow.key == "ee") {
				thisConference.externalReference.add(thisRow.value.toString());
			} else if (thisRow.key == "isbn") {
				thisConference.isbn = thisRow.value.toString();
			} else if (thisRow.key == "publisher") {
				thisConference.publisher = thisRow.value.toString();
			} else if (thisRow.key == "series") {
				thisConference.seriesTitle = thisRow.value.toString();
				thisConference.seriesUrl = thisRow.getAttribute("href");
			}
		}
		return thisConference;
	}

	public static void main(String[] args) {

		try {
			QueryEngineProviderService queryEngine = new QueryEngineProviderService();
			FrontEnd myFrontEnd = new FrontEnd();
			XMLParser myParser = new XMLParser();
			RawRecordCollection parsedRecords = myParser.parse("dblp.xml"); // set up
																	// local XML
																	// sample to
																	// test
			System.out.println("Parsing is done!");
			PublicationEntityCollection pubs = myFrontEnd.convert(parsedRecords, "dblp-test2");
			queryEngine.executeWriteQuery("dblp-test2", pubs);
			System.out.println("Successfully populated to elastic search");
			// verify if data is saved to ES
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
