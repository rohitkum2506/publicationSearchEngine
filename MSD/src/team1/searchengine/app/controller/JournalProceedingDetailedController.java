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
import team1.searchengine.model.Author;
import team1.searchengine.model.Journal;
import team1.searchengine.model.PublicationEntityCollection;

public class JournalProceedingDetailedController {
	private HomeController homeCtrl;

	@FXML
	private Label lblTitle, lblYear, lblBookTitle, lblPublisher, lblSeriesTitle, lblVolume, lblIsbn;

	@FXML
	private Pane pneYear, pneBookTitle, pnePublisher, pneVolume, pneSeriesTitle, pneIsbn;

	@FXML
	private Hyperlink hlnkUrl;

	@FXML
	private GridPane grdContentWrap;

	private Journal journal;

	public void init(Journal journal, HomeController homeCtrl) {
		ArrayList<Author> authors = new ArrayList<>();
		this.journal = journal;
		this.homeCtrl = homeCtrl;

		// collect all authors
		if (journal.getPublications() != null && !journal.getPublications().isEmpty())
			journal.getPublications().forEach(pub -> {
				authors.addAll(pub.getAuthors());
			});

		// remove duplicates in collected authors.
		PublicationEntityCollection pbe = new PublicationEntityCollection();
		pbe.setAuthors(authors);
		pbe = homeCtrl.filterDuplicates(pbe);
		ArrayList<Author> finalAuthors = pbe.getAuthors();
		
		loadView(journal, finalAuthors);

	}

	/**
	 * Load the view
	 * 
	 * @param journal
	 * @param authors
	 */
	private void loadView(Journal journal, ArrayList<Author> authors) {
		try {
			
			lblTitle.setText(journal.getTitle());
			if (journal.getYear() == 0) {
				pneYear.setVisible(false);
				pneYear.setManaged(false);
			} else {
				lblYear.setText(String.valueOf(journal.getYear()));
			}

			if (journal.getPublisher() != null && !journal.getPublisher().isEmpty())
				lblPublisher.setText(journal.getPublisher());
			else {
				pnePublisher.setVisible(false);
				pnePublisher.setManaged(false);
			}

			if (journal.getBookTitle() != null && !journal.getBookTitle().isEmpty())
				lblBookTitle.setText(journal.getBookTitle());
			else {
				pneBookTitle.setVisible(false);
				pneBookTitle.setManaged(false);
			}

			if (journal.getSeriesTitle() != null && !journal.getSeriesTitle().isEmpty())
				lblSeriesTitle.setText(journal.getSeriesTitle());
			else {
				pneSeriesTitle.setVisible(false);
				pneSeriesTitle.setManaged(false);
			}

			if (journal.getVolume() != 0)
				lblVolume.setText(String.valueOf(journal.getVolume()));
			else {
				pneVolume.setVisible(false);
				pneVolume.setManaged(false);
			}

			if (journal.getIsbn() != null && !journal.getIsbn().isEmpty()) {
				lblIsbn.setText(journal.getIsbn());
			} else {
				pneIsbn.setVisible(false);
				pneIsbn.setManaged(false);
			}

			if (journal.getUrl() == null || journal.getUrl().isEmpty()) {
				hlnkUrl.setVisible(false);
				hlnkUrl.setManaged(false);
			}

			grdContentWrap.getChildren().clear();
			int rowIndex = 0;

			// Editors
			if (!journal.getEditors().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("EDITORS");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				Collections.sort(journal.getEditors(), (a, b) -> a.getRawName().compareTo(b.getRawName()));

				for (int i = 0; i < journal.getEditors().size(); i++) {
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/AuthorResultItemView.fxml"));
					VBox aResultItem = thisLoader.load();
					AuthorResultItemController aResItemCtrl = thisLoader.getController();
					aResItemCtrl.init(homeCtrl, journal.getEditors().get(i));
					grdContentWrap.addRow(rowIndex++, aResultItem);
				}
			}

			// Publications
			if (journal.getPublications() != null && !journal.getPublications().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("PUBLICATIONS");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				for (int i = 0; i < journal.getPublications().size(); i++) {
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/JournalPublicationResultItemView.fxml"));
					VBox cPubResultItem = thisLoader.load();
					JournalPublicationResultItemController cPubResItemCtrl = thisLoader.getController();
					cPubResItemCtrl.init(homeCtrl, journal.getPublications().get(i));
					grdContentWrap.addRow(rowIndex++, cPubResultItem);
				}
			}

			if (!authors.isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("AUTHORS IN THIS JOURNAL");
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
			ex.printStackTrace();
		}
	}

	public void onBrowserLinkClicked(ActionEvent e) {
		homeCtrl.openUrl(this.journal.getUrl());
	}
}
