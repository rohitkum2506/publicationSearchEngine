package team1.searchengine.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import team1.searchengine.model.ConferencePublication;

public class ConferencePublicationResultItemController {

	HomeController homeCtrl;
	ConferencePublication confPub;

	@FXML
	private Hyperlink hlnkPubTitle;

	@FXML
	private Label lblConferenceTitle;

	@FXML
	private Label lblBookTitle;

	@FXML
	private Label lblYear;

	@FXML
	private FlowPane fpneAuthorsWrap;

	@FXML
	private GridPane grdAuthors;

	private void clearFields() {
		this.hlnkPubTitle.setText(null);
		lblConferenceTitle.setText(null);
		lblBookTitle.setText(null);
		lblYear.setText(null);
	}

	public void init(HomeController homeCtrl, ConferencePublication cPub) {
		clearFields();
		this.homeCtrl = homeCtrl;
		this.confPub = cPub;
		hlnkPubTitle.setText(this.confPub.getTitle());
		if (this.confPub.getConference() != null)
			lblConferenceTitle.setText(this.confPub.getConference().getTitle());
		if (this.confPub.getYear() != 0)
			lblYear.setText(String.valueOf(this.confPub.getYear()));
		lblBookTitle.setText(this.confPub.getBookTitle());
		if (this.confPub.getAuthors() != null) {

//			for (int i = 0; i < this.confPub.getAuthors().size(); i++) {
//				Author thisAuthor = this.confPub.getAuthors().get(i);
//				Hyperlink thisAuthorLink = new Hyperlink(thisAuthor.getName());
//				thisAuthorLink.setOnAction(e -> {
//					homeCtrl.loadDetailedView(thisAuthor);
//				});
//				grdAuthors.addRow(i, thisAuthorLink);
//			}

			this.confPub.getAuthors().forEach(a -> {
				Hyperlink thisAuthorLink = new Hyperlink(a.getName());
				thisAuthorLink.setOnAction(e -> {
					homeCtrl.loadDetailedView(a);
				});
				fpneAuthorsWrap.getChildren().add(thisAuthorLink);
			});
		}
	}

	public void onTitleClick(ActionEvent e) {
		homeCtrl.loadDetailedView(confPub);
	}
}
