package team1.searchengine.app.controller;

import team1.searchengine.model.*;

public interface DetailedViewVisitor {

	/**
	 * Load the Author details in the Preview pane
	 * 
	 * @param author
	 */
	public void loadDetailedView(Author author);

	/**
	 * Load the Journal Publication details in the Preview pane
	 * 
	 * @param journalPub
	 */
	public void loadDetailedView(JournalPublication journalPub);

	/**
	 * Load the Conference Publication details in the Preview pane
	 * 
	 * @param conferencePub
	 */
	public void loadDetailedView(ConferencePublication conferencePub);

	/**
	 * Load the Journal details in the Preview pane
	 * 
	 * @param journal
	 */
	public void loadDetailedView(Journal journal);

	/**
	 * Load the Conference details in the Preview pane
	 * 
	 * @param journal
	 */
	public void loadDetailedView(Conference journal);
}
