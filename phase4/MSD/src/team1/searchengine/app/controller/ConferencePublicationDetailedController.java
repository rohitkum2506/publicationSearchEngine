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
import team1.searchengine.model.ConferencePublication;

public class ConferencePublicationDetailedController {
	private HomeController homeCtrl;

	@FXML
	private Label lblTitle, lblYear, lblBookTitle, lblIssueNo;

	@FXML
	private Pane pneYear, pneIssueNo, pneBookTitle, pneLinks, pneLinkItems;

	@FXML
	private Hyperlink hlnkUrl, hlnkConference;

	@FXML
	private GridPane grdContentWrap;

	private ConferencePublication conferencePub;

	/**
	 * Initialize the viewer
	 * 
	 * @param journalPub
	 * @param homeCtrl
	 */
	public void init(ConferencePublication conferencePub, HomeController homeCtrl) {
		this.conferencePub = conferencePub;
		this.homeCtrl = homeCtrl;
		loadView();
	}

	/**
	 * Load view for the Journal Publication
	 */
	private void loadView() {

		try {
			lblTitle.setText(this.conferencePub.getTitle());
			if (this.conferencePub.getYear() == 0) {
				pneYear.setVisible(false);
				pneYear.setPrefWidth(0);
			} else
				lblYear.setText(String.valueOf(this.conferencePub.getYear()));

			if (this.conferencePub.getConference() != null)
				hlnkConference.setText(this.conferencePub.getConference().getTitle());

			if (this.conferencePub.getIssueNumber() != 0)
				lblIssueNo.setText(String.valueOf(this.conferencePub.getIssueNumber()));
			else {
				pneIssueNo.setVisible(false);
				pneIssueNo.setPrefWidth(0);
			}

			if (this.conferencePub.getBookTitle() != null && !this.conferencePub.getBookTitle().isEmpty())
				lblBookTitle.setText(this.conferencePub.getBookTitle());
			else {
				pneBookTitle.setVisible(false);
				pneBookTitle.setPrefWidth(0);
			}

			if (this.conferencePub.getExternalReference() != null
					&& !this.conferencePub.getExternalReference().isEmpty()) {
				this.conferencePub.getExternalReference().forEach(ee -> {
					Hyperlink thisLink = new Hyperlink("Link");
					thisLink.setOnAction(e -> {
						homeCtrl.openUrl(ee);
					});
					pneLinkItems.getChildren().add(thisLink);
				});
			} else
				pneLinks.setVisible(false);

			if (this.conferencePub.getUrl() == null || this.conferencePub.getUrl().isEmpty()) {
				hlnkUrl.setVisible(false);
			}

			grdContentWrap.getChildren().clear();
			int rowIndex = 0;

			if (!this.conferencePub.getAuthors().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = (AnchorPane) sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("AUTHORS");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);

				ArrayList<Author> authors = this.conferencePub.getAuthors();
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

	/**
	 * Handle click on URLs
	 * 
	 * @param e
	 */
	public void onBrowserLinkClicked(ActionEvent e) {
		homeCtrl.openUrl(this.conferencePub.getUrl());
	}

	/**
	 * Handle click event on the Journal Name
	 * 
	 * @param e
	 */
	public void onConferenceClicked(ActionEvent e) {
		homeCtrl.loadDetailedView(this.conferencePub.conference);
	}
}
