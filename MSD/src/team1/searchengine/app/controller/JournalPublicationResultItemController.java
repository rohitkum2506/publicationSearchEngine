package team1.searchengine.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import team1.searchengine.model.JournalPublication;

public class JournalPublicationResultItemController {

	HomeController homeCtrl;
	JournalPublication journalPub;

	@FXML
	private Hyperlink hlnkPubTitle;

	@FXML
	private Label lblBookTitle;
	
	@FXML
	private Label lblYear;

	@FXML
	private FlowPane fpneAuthorsWrap;

	private void clearFields(){
		hlnkPubTitle.setText(null);
		lblBookTitle.setText(null);
		lblYear.setText(null);
		fpneAuthorsWrap.getChildren().clear();
	}
	
	public void init(HomeController homeCtrl, JournalPublication jPub) {
		clearFields();
		this.homeCtrl = homeCtrl;
		this.journalPub = jPub;
		hlnkPubTitle.setText(this.journalPub.getTitle());
		if (this.journalPub.getJournal() != null)
			lblBookTitle.setText(this.journalPub.getJournal().getTitle());
		if(this.journalPub.getYear() != 0){
			lblYear.setText(String.valueOf(this.journalPub.getYear()));
		}
		if(this.journalPub.getAuthors() != null)
			this.journalPub.getAuthors().forEach(a ->{
				Hyperlink thisAuthorLink = new Hyperlink(a.getName());
				
				thisAuthorLink.setOnAction(e -> {
					homeCtrl.loadDetailedView(a);
				});
				fpneAuthorsWrap.getChildren().add(thisAuthorLink);
			});
	}

	public void onTitleClick(ActionEvent e) {
		homeCtrl.loadDetailedView(journalPub);
	}
}
