package team1.searchengine.app.controller;


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
import team1.searchengine.model.JournalPublication;

public class JournalPublicationDetailedController {
	private HomeController homeCtrl;

	@FXML
	private Label lblTitle, lblYear, lblVolume, lblIssueNo;

	@FXML
	private Pane pneJournal, pneYear, pneIssueNo, pneVolume, pneLinks, pneLinkItems;

	@FXML
	private AnchorPane apneParent;
	
	@FXML
	private Hyperlink hlnkUrl, hlnkJournal;

	@FXML
	private GridPane grdContentWrap;

	private JournalPublication journalPub;

	/**
	 * Initialize the viewer 
	 * @param journalPub
	 * @param homeCtrl
	 */
	public void init(JournalPublication journalPub, HomeController homeCtrl) {
		this.journalPub = journalPub;
		this.homeCtrl = homeCtrl;
		loadView();
	}

	/**
	 * Load view for the Journal Publication
	 */
	private void loadView() {
		
		try {
			lblTitle.setText(this.journalPub.getTitle());
			if (this.journalPub.getYear() == 0){
				pneYear.setVisible(false);
				pneYear.setPrefWidth(0);
			}
			else
				lblYear.setText(String.valueOf(this.journalPub.getYear()));

			if (this.journalPub.getJournal() != null)
				hlnkJournal.setText(this.journalPub.getJournal().getTitle());
			else{
				pneJournal.setVisible(false);
				pneJournal.setPrefWidth(0);
			}

			if (this.journalPub.getIssueNumber() != 0)
				lblIssueNo.setText(String.valueOf(this.journalPub.getIssueNumber()));
			else{
				pneIssueNo.setVisible(false);
				pneIssueNo.setPrefWidth(0);
			}
			
			if (this.journalPub.getVolume() != 0)
				lblVolume.setText(String.valueOf(this.journalPub.getVolume()));
			else{
				pneVolume.setVisible(false);
				pneVolume.setPrefWidth(0);
				}

			if (this.journalPub.getExternalReference() != null && !this.journalPub.getExternalReference().isEmpty()) {
				this.journalPub.getExternalReference().forEach(ee -> {
					Hyperlink thisLink = new Hyperlink("Link");
					thisLink.setOnAction(e ->{
						homeCtrl.openUrl(ee);
					});
					pneLinkItems.getChildren().add(thisLink);
				});
			}
			else
				pneLinks.setVisible(false);
			

			if (this.journalPub.getUrl() == null || this.journalPub.getUrl().isEmpty()) {
				hlnkUrl.setVisible(false);
			}

			grdContentWrap.getChildren().clear();
			int rowIndex = 0;

			if (!this.journalPub.getAuthors().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/SidePaneSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = (AnchorPane) sectionTitleLoader.load();
				SidePaneSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("AUTHORS");
				grdContentWrap.addRow(rowIndex++, sectionTitleElement);
				
				for (int i = 0; i < this.journalPub.getAuthors().size(); i++) {
					FXMLLoader thisLoader = new FXMLLoader();
					thisLoader.setLocation(AppMain.class.getResource("view/AuthorResultItemView.fxml"));
					VBox aResultItem = thisLoader.load();
					AuthorResultItemController aResItemCtrl = thisLoader.getController();
					aResItemCtrl.init(homeCtrl, this.journalPub.getAuthors().get(i));
					grdContentWrap.addRow(rowIndex++, aResultItem);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Handle click on URLs
	 * @param e
	 */
	public void onBrowserLinkClicked(ActionEvent e) {
		homeCtrl.openUrl(this.journalPub.getUrl());
	}
	
	/**
	 * Handle click event on the Journal Name
	 * @param e
	 */
	public void onJournalClicked(ActionEvent e){
		homeCtrl.loadDetailedView(this.journalPub.journal);
	}
}
