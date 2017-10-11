package team1.searchengine.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import team1.searchengine.app.AppMain;
import team1.searchengine.ui.ShortlistManager;
import team1.searchengine.ui.ShortlistManager.ShortlistAuthor;

public class ShortlistController {
	private HomeController homeCtrl;

	@FXML
	private GridPane grdContentWrap;

	public void init(HomeController homeCtrl) {
		this.homeCtrl = homeCtrl;
		loadShortlist();
	}

	private void loadShortlist() {
		grdContentWrap.getChildren().clear();
		for (int i = 0; i < ShortlistManager.getShortlist().size(); i++) {
			ShortlistAuthor thisShortlistedAuthor = ShortlistManager.getShortlist().get(i);
			try {
				FXMLLoader thisLoader = new FXMLLoader();
				thisLoader.setLocation(AppMain.class.getResource("view/ShortListItemView.fxml"));
				VBox aResultItem = thisLoader.load();
				ShortlistItemController aResItemCtrl = thisLoader.getController();
				aResItemCtrl.init(homeCtrl, thisShortlistedAuthor);
				grdContentWrap.addRow(i, aResultItem);
			} catch (Exception ex) {
				homeCtrl.setError(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
