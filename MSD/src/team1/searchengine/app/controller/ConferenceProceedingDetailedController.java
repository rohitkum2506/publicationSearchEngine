package team1.searchengine.app.controller;

import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import team1.searchengine.app.AppMain;
import team1.searchengine.logging.Logger;
import team1.searchengine.model.Author;
import team1.searchengine.model.Conference;
import team1.searchengine.model.PublicationEntityCollection;

public class ConferenceProceedingDetailedController {
	private HomeController homeCtrl;

	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblYear;
	
	@FXML
	private Label lblBookTitle;
	
	@FXML
	private Label lblPublisher;
	
	@FXML
	private Label lblSeriesTitle;
	
	@FXML
	private Label lblVolume;
	
	@FXML
	private Label lblIsbn;

	@FXML
	private Pane pneYear;
	
	@FXML
	private Pane pneBookTitle;
	
	@FXML
	private Pane pnePublisher;
	
	@FXML
	private Pane pneVolume;
	
	@FXML
	private Pane pneSeriesTitle;
	
	@FXML
	private Pane pneIsbn;

	@FXML
	private Hyperlink hlnkUrl;

	@FXML
	private GridPane grdContentWrap;

	private Conference conference;

	/**
	 * Initialize and load the detailed conference view
	 * @param conference
	 * @param homeCtrl
	 */
	public void init(Conference conference, HomeController homeCtrl) {
		ArrayList<Author> authors = new ArrayList<>();
		this.conference = conference;

		this.homeCtrl = homeCtrl;
		if (conference.getPublications() != null && !conference.getPublications().isEmpty())
			conference.getPublications().forEach(pub -> {
				authors.addAll(pub.getAuthors());
			});

		// remove duplicates in collected authors.
		PublicationEntityCollection pbe = new PublicationEntityCollection();
		pbe.setAuthors(authors);
		pbe = homeCtrl.filterDuplicates(pbe);
		ArrayList<Author> finalAuthors = pbe.getAuthors();

		loadView(conference, finalAuthors);
	}

	/**
	 * Load the detailed conference view
	 * @param conference Conference
	 * @param authors list of authors in this conference
	 */
	private void loadView(Conference conference, ArrayList<Author> authors) {
		try {

			lblTitle.setText(conference.getTitle());
			if (conference.getYear() == 0) {
				pneYear.setVisible(false);
				pneYear.setManaged(false);
			} else
				lblYear.setText(String.valueOf(conference.getYear()));

			if (conference.getPublisher() != null && !conference.getPublisher().isEmpty()) {
				lblPublisher.setText(conference.getPublisher());
			} else {
				pnePublisher.setVisible(false);
				pnePublisher.setManaged(false);
			}

			if (conference.getBookTitle() != null && !conference.getBookTitle().isEmpty()) {
				lblBookTitle.setText(conference.getBookTitle());
			} else {
				pneBookTitle.setVisible(false);
				pneBookTitle.setManaged(false);
			}

			if (conference.getSeriesTitle() != null && !conference.getSeriesTitle().isEmpty())
				lblSeriesTitle.setText(conference.getSeriesTitle());
			else {
				pneSeriesTitle.setVisible(false);
				pneSeriesTitle.setManaged(false);
			}

			if (conference.getVolume() != 0)
				lblVolume.setText(String.valueOf(conference.getVolume()));
			else {
				pneVolume.setVisible(conference.getVolume() != 0);
				pneVolume.setManaged(conference.getVolume() != 0);
			}

			if (conference.getUrl() == null || conference.getUrl().isEmpty()) {
				hlnkUrl.setVisible(false);
			}

			if (conference.getIsbn() != null && !conference.getIsbn().isEmpty()) {
				lblIsbn.setText(conference.getIsbn());
			}

			grdContentWrap.getChildren().clear();
			int rowIndex = 0;

			// Editors
			if (!conference.getEditors().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("EDITORS");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				for (int i = 0; i < conference.getEditors().size(); i++) {
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/AuthorResultItemView.fxml"));
					VBox aResultItem = thisLoader.load();
					AuthorResultItemController aResItemCtrl = thisLoader.getController();
					aResItemCtrl.init(homeCtrl, conference.getEditors().get(i));
					grdContentWrap.addRow(rowIndex++, aResultItem);
				}
			}

			// Publications
			if (conference.getPublications() != null && !conference.getPublications().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("PUBLICATIONS");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				for (int i = 0; i < conference.getPublications().size(); i++) {
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/ConferencePublicationResultItemView.fxml"));
					VBox cPubResultItem = thisLoader.load();
					ConferencePublicationResultItemController cPubResItemCtrl = thisLoader.getController();
					cPubResItemCtrl.init(homeCtrl, conference.getPublications().get(i));
					grdContentWrap.addRow(rowIndex++, cPubResultItem);
				}
			}

			if (!authors.isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("AUTHORS IN THIS CONFERENCE");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				Collections.sort(authors, (a, b) -> a.getRawName().compareTo(b.getRawName()));

				for (int i = 0; i < authors.size(); i++) {
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/AuthorResultItemView.fxml"));
					VBox aResultItem = thisLoader.load();
					AuthorResultItemController aResItemCtrl = thisLoader.getController();
					aResItemCtrl.init(homeCtrl, authors.get(i));
					grdContentWrap.addRow(rowIndex++, aResultItem);
				}
			}
		} catch (Exception ex) {
			Logger.log("Error loading Conference detailed view. "+ ex.getMessage(), "UI-ERROR");
		}
	}

	/**
	 * Handle click on the conference name
	 * @param e
	 */
	public void onBrowserLinkClicked(ActionEvent e) {
		homeCtrl.openUrl(this.conference.getUrl());
	}
}
