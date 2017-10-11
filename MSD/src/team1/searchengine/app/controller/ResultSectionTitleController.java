package team1.searchengine.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultSectionTitleController {
	@FXML
	private Label lblSectionTitle;

	public void setTitle(String title){
		lblSectionTitle.setText(title);
	}
}
