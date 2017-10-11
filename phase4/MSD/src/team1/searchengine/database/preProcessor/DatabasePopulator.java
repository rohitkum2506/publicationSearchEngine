package team1.searchengine.database.preProcessor;

import java.util.ArrayList;
import java.util.List;

import team1.searchengine.database.QueryService;
import team1.searchengine.model.*;
import team1.searchengine.model.PublicationEntityCollection;

public class DatabasePopulator {
	private ArrayList<EntityPopulator> populators = new ArrayList<EntityPopulator>();
	private AuthorPopulator authorPopulator;;
	private JournalPopulator journalPopulator;
	private ConferencePopulator conferencePopulator;
	private ConferencePublicationPopulator conferencePublicationPopulator;
	private JournalPublicationPopulator journalPublicationPopulator;
	
	/**
	 *  Constructor
	 */
	public DatabasePopulator(QueryService qs) {
		authorPopulator = new AuthorPopulator(qs);
		journalPopulator = new JournalPopulator(qs);
		conferencePopulator= new ConferencePopulator(qs);
		conferencePublicationPopulator = new ConferencePublicationPopulator(qs);
		journalPublicationPopulator = new JournalPublicationPopulator(qs); 
	}

	/**
	 * use this function to populate the database after the parsing of XML is done. 
	 * Fetches the individual elements from the collection and insers data in elastic search 
	 * @param entityCollection
	 */
	public void PrePopulatorDatabase(String indexname, PublicationEntityCollection entityCollection) {
		ArrayList<Author> authors = entityCollection.getAuthors();
		ArrayList<Journal> journals = entityCollection.getJournals();
		ArrayList<Conference> conferences = entityCollection.getConferences();
		ArrayList<ConferencePublication> conferencePublications = entityCollection.getConferencePublications();
		ArrayList<JournalPublication> journalPublications = entityCollection.getJournalPublications();
		
		authorPopulator.populateBulk(indexname, authors);
		journalPopulator.populateBulk(indexname, journals);
		conferencePopulator.populateBulk(indexname, conferences);
		conferencePublicationPopulator.populateBulk(indexname, conferencePublications);
		journalPublicationPopulator.populateBulk(indexname, journalPublications);
	}

	public void populateEntities(String index, List<PublicationEntity> entities) {
		for (PublicationEntity entity : entities) {
			entity.getPopulator().populate(index, entity);
		}
	}

}
