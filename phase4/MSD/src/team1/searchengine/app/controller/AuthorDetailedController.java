package team1.searchengine.app.controller;

import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import team1.searchengine.app.AppMain;
import team1.searchengine.logging.Logger;
import team1.searchengine.model.Author;
import team1.searchengine.model.PublicationEntityCollection;
import team1.searchengine.ui.ShortlistManager;

public class AuthorDetailedController {
	private HomeController homeCtrl;
	private Author author;

	@FXML
	private Label lblAuthorName;

	@FXML
	private Label lblAlias;

	@FXML
	private FlowPane fpneAuthorDetails;

	@FXML
	private Pane pneIsEditor;

	@FXML
	private Pane pneIsConferenceChair;

	@FXML
	private Pane pneIsGeneralChair;

	@FXML
	private Pane pneIsProgramChair;

	@FXML
	private Pane pneIsExternalReviewCommittee;

	@FXML
	private Pane pneIsCommitteeMember;

	@FXML
	private GridPane grdContentWrap;

	@FXML
	private Button btnAddToShortlist;

	@FXML
	private Button btnRemoveFromShortlist;

	/**
	 * Initialize and load the Author Detailed display
	 * 
	 * @param author
	 *            Author
	 * @param pubs
	 *            list of publications of the author
	 * @param edits
	 *            list of conference or journals where the author had been an
	 *            editor
	 * @param homeCtrl
	 */
	public void init(Author author, PublicationEntityCollection pubs, PublicationEntityCollection edits,
			HomeController homeCtrl) {
		ArrayList<Author> connections = new ArrayList<>();
		this.author = author;
		this.homeCtrl = homeCtrl;

		// get list of all co-authors in conference publications
		pubs.conferencePublications.forEach(thisPub -> {
			thisPub.authors.forEach(a -> {
				if (!a.getRawName().equals(author.name))
					connections.add(a);
			});
		});

		// get list of all co-authors in journal publications
		pubs.journalPublications.forEach(thisPub -> {
			thisPub.authors.forEach(a -> {
				if (!a.getRawName().equals(author.name))
					connections.add(a);
			});
		});

		loadView(author, connections, pubs, edits);
	}

	/**
	 * Load the detailed view of the author
	 * 
	 * @param thisAuthor
	 * @param connections
	 * @param pubs
	 * @param edits
	 */
	private void loadView(Author thisAuthor, ArrayList<Author> connections, PublicationEntityCollection pubs,
			PublicationEntityCollection edits) {

		btnAddToShortlist.setVisible(!ShortlistManager.existsInShortlist(thisAuthor));

		try {
			lblAuthorName.setText(thisAuthor.getName());
			if (thisAuthor.getAlias() != null && thisAuthor.getAlias().length() > 0)
				lblAlias.setText(thisAuthor.getAlias());
			else
				lblAlias.setText(null);

			if (!thisAuthor.getHasBeenEditor())
				fpneAuthorDetails.getChildren().remove(pneIsEditor);

			if (!thisAuthor.isConferenceChair())
				fpneAuthorDetails.getChildren().remove(pneIsConferenceChair);

			if (!thisAuthor.isGeneralChair())
				fpneAuthorDetails.getChildren().remove(pneIsGeneralChair);

			if (!thisAuthor.isProgramChair())
				fpneAuthorDetails.getChildren().remove(pneIsProgramChair);

			if (!thisAuthor.isInExternalReviewComittee())
				fpneAuthorDetails.getChildren().remove(pneIsExternalReviewCommittee);

			if (!thisAuthor.isCommitteeMember())
				fpneAuthorDetails.getChildren().remove(pneIsCommitteeMember);

			grdContentWrap.getChildren().clear();
			int rowIndex = 0;

			// journal publications of author
			if (!pubs.getJournalPublications().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("JOURNAL PUBLICATIONS");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				for (int i = 0; i < pubs.getJournalPublications().size(); i++) {
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/JournalPublicationResultItemView.fxml"));
					VBox jPubResultItem = thisLoader.load();
					JournalPublicationResultItemController jPubResItemCtrl = thisLoader.getController();
					jPubResItemCtrl.init(homeCtrl, pubs.getJournalPublications().get(i));
					grdContentWrap.addRow(rowIndex++, jPubResultItem);
				}
			}

			// conference publications of author
			if (!pubs.getConferencePublications().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("CONFERENCE PUBLICATIONS");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				for (int i = 0; i < pubs.getConferencePublications().size(); i++) {
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/ConferencePublicationResultItemView.fxml"));
					VBox cPubResultItem = thisLoader.load();
					ConferencePublicationResultItemController cPubResItemCtrl = thisLoader.getController();
					cPubResItemCtrl.init(homeCtrl, pubs.getConferencePublications().get(i));
					grdContentWrap.addRow(rowIndex++, cPubResultItem);
				}
			}

			// Journal Editor-ship
			if (!edits.getJournals().isEmpty()) {
				pneIsEditor.setVisible(true);
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("EDITOR");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				for (int i = 0; i < edits.getJournals().size(); i++) {
					connections.addAll(edits.getJournals().get(i).getEditors());
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/ProceedingResultItemView.fxml"));
					VBox proceedingResultItem = thisLoader.load();
					ProceedingResultItemController proceedingResItemCtrl = thisLoader.getController();
					proceedingResItemCtrl.init(homeCtrl, edits.getJournals().get(i));
					grdContentWrap.addRow(rowIndex++, proceedingResultItem);
				}
			}

			// Conference Editor-ship
			if (!edits.getConferences().isEmpty()) {
				pneIsGeneralChair.setVisible(true);
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("PROGRAM CHAIR");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				for (int i = 0; i < edits.getConferences().size(); i++) {
					connections.addAll(edits.getConferences().get(i).getEditors());
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/ProceedingResultItemView.fxml"));
					VBox proceedingResultItem = thisLoader.load();
					ProceedingResultItemController proceedingResItemCtrl = thisLoader.getController();
					proceedingResItemCtrl.init(homeCtrl, edits.getConferences().get(i));
					grdContentWrap.addRow(rowIndex++, proceedingResultItem);
				}
			}

			if (!connections.isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("SIMILAR TO");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				PublicationEntityCollection p = new PublicationEntityCollection();
				p.setAuthors(connections);
				p = homeCtrl.filterDuplicates(p);
				connections = p.getAuthors();

				Collections.sort(connections, (a, b) -> a.getRawName().compareTo(b.getRawName()));

				for (int i = 0; i < connections.size(); i++) {
					if (connections.get(i).getName().equals(thisAuthor.getName()))
						continue;
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/AuthorResultItemView.fxml"));
					VBox aResultItem = thisLoader.load();
					AuthorResultItemController aResItemCtrl = thisLoader.getController();
					aResItemCtrl.init(homeCtrl, connections.get(i));
					grdContentWrap.addRow(rowIndex++, aResultItem);
				}
			}
		} catch (Exception ex) {
			Logger.log("Error loading Author detailed view. " + ex.getMessage(), "UI-ERROR");
		}

	}

	/**
	 * Add current author to shortlist
	 * 
	 * @param e
	 */
	public void onAddToShortlist(ActionEvent e) {
		ShortlistManager.addToShortlist(author, null, "");
		btnAddToShortlist.setVisible(false);
		homeCtrl.updateShortlistCount();
	}

	/**
	 * Remove current author from shortlist
	 * 
	 * @param e
	 */
	public void onRemoveFromShortlist(ActionEvent e) {
		ShortlistManager.removeFromShortlist(author);
		btnAddToShortlist.setVisible(true);
		homeCtrl.updateShortlistCount();
	}
}
