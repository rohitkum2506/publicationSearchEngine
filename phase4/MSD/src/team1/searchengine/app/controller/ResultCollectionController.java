package team1.searchengine.app.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class ResultCollectionController {
	private int rowIndex=0;
	
	@FXML
	private Accordion accAccordian;
	
	@FXML
	private TitledPane tpnePaneHeader;

	@FXML
	private GridPane grdResultContainer;

	public void setTitle(String title) {
		tpnePaneHeader.setText(title);
		tpnePaneHeader.setExpanded(true);
		accAccordian.setExpandedPane(tpnePaneHeader);
	}
	
	public GridPane getGridContainer(){
		return grdResultContainer;
	}
	
	public void addItem(Node e){
		grdResultContainer.addRow(rowIndex++, e);
	}
}
