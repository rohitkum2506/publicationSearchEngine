package team1.searchengine.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import team1.searchengine.model.GenericProceeding;

public class ProceedingResultItemController {

	HomeController homeCtrl;
	GenericProceeding proceeding;

	@FXML
	private Hyperlink hlnkProceedingTitle;

	@FXML
	private Label lblSeriesTitle;
	
	@FXML
	private Label lblBookTitle;
	
	@FXML
	private Label lblYear;

	@FXML
	private FlowPane fpneEditorsWrap;

	private void clearFields(){
		hlnkProceedingTitle.setText(null);
		lblBookTitle.setText(null);
		lblSeriesTitle.setText(null);
		lblYear.setText(null);
	}
	
	public void init(HomeController homeCtrl, GenericProceeding proceeding) {
		clearFields();
		this.homeCtrl = homeCtrl;
		this.proceeding = proceeding;
		hlnkProceedingTitle.setText(this.proceeding.getTitle());
		lblBookTitle.setText(this.proceeding.getBookTitle());
		lblSeriesTitle.setText(this.proceeding.getSeriesTitle());
		if(this.proceeding.getYear() != 0){
			lblYear.setText(String.valueOf(this.proceeding.getYear()));
		}
		if(this.proceeding.getEditors() != null)
			this.proceeding.getEditors().forEach(a ->{
				Hyperlink thisAuthorLink = new Hyperlink(a.getName());
				thisAuthorLink.setPadding(new Insets(0.0, 5.0, 0.0, 5.0));
				thisAuthorLink.setOnAction(e -> {
					homeCtrl.loadDetailedView(a);
				});
				fpneEditorsWrap.getChildren().add(thisAuthorLink);
			});
	}

	public void onTitleClick(ActionEvent e) {
		homeCtrl.loadDetailedView(proceeding);
	}
}
