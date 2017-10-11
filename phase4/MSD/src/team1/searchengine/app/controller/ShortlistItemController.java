package team1.searchengine.app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import team1.searchengine.model.Author;
import team1.searchengine.ui.AuthorPosition;
import team1.searchengine.ui.ShortlistManager;
import team1.searchengine.ui.ShortlistManager.ShortlistAuthor;

public class ShortlistItemController {
	private HomeController homeCtrl;
	private Author thisAuthor;
	private ShortlistAuthor thisShortlisedAuthor;

	@FXML
	private Button btnRemove;
	
	@FXML
	private Button btnSave;

	@FXML
	private Hyperlink hlnkAuthorName;

	@FXML
	private ComboBox<AuthorPosition> cmbPosition;

	@FXML
	private TextField txtNotes;

	@FXML
	private AnchorPane apneView;
	
	@FXML
	private AnchorPane apneEdit;

	@FXML
	private Label lblNotes;

	@FXML
	private Pane pneIsGeneralChair;

	@FXML
	private Pane pneIsEditor;

	@FXML
	private Pane pneIsProgramChair;

	@FXML
	private Pane pneIsConferenceChair;

	@FXML
	private Pane pneIsExternalReviewCommittee;

	@FXML
	private Pane pneIsMisc;

	public void init(HomeController homeCtrl, ShortlistAuthor shortlistedAuthor) {
		this.homeCtrl = homeCtrl;
		this.thisShortlisedAuthor = shortlistedAuthor;

		ObservableList<AuthorPosition> positionObservableList = FXCollections
				.observableArrayList(AuthorPosition.values());
		cmbPosition.setItems(positionObservableList);
		// render drop down for content types
		cmbPosition.setCellFactory(combobox -> {
			return new ListCell<AuthorPosition>() {
				@Override
				protected void updateItem(AuthorPosition item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.toString());
					}
				}
			};
		});

		// render drop down selected item
		cmbPosition.setConverter(new StringConverter<AuthorPosition>() {
			@Override
			public String toString(AuthorPosition item) {
				if (item == null) {
					return null;
				} else {
					return item.toString();
				}
			}

			@Override
			public AuthorPosition fromString(String arg0) {
				return null;
			}
		});

		if (shortlistedAuthor.getAuthor() == null)
			throw new IllegalArgumentException("Author cannot be null");
		this.thisAuthor = shortlistedAuthor.getAuthor();
		hlnkAuthorName.setText(shortlistedAuthor.getAuthor().getName());

		if (shortlistedAuthor.getPosition() != null) {
			cmbPosition.getSelectionModel().select(shortlistedAuthor.getPosition());
		}
		setUIElements();
	}

	/**
	 * Handle click on Author name
	 * 
	 * @param e
	 */
	public void onAuthorClick(ActionEvent e) {
		homeCtrl.loadDetailedView(thisAuthor);
	}

	/**
	 * Handle removal of author from list
	 * 
	 * @param e
	 */
	public void onRemoveClicked(ActionEvent e) {
		ShortlistManager.removeFromShortlist(thisAuthor);
		homeCtrl.updateShortlistCount();
		homeCtrl.onShortListClick(null);
	}

	/**
	 * Handle request to edit meta fields
	 * 
	 * @param e
	 */
	public void onEditClicked(MouseEvent e) {
		apneView.setVisible(false);
		apneEdit.setVisible(true);
	}

	/**
	 * save the shortlist
	 * 
	 * @param e
	 */
	public void onSaveClicked(ActionEvent e) {
		thisShortlisedAuthor.setPosition(cmbPosition.getSelectionModel().getSelectedItem());
		thisShortlisedAuthor.setNotes(txtNotes.getText());
		ShortlistManager.save();

		apneEdit.setVisible(false);
		apneView.setVisible(true);

		setUIElements();
	}

	/**
	 * Set the Position element visibility based on Position of the author
	 */
	private void setUIElements() {

		if (thisShortlisedAuthor.getNotes() != null && !thisShortlisedAuthor.getNotes().isEmpty()) {
			txtNotes.setText(thisShortlisedAuthor.getNotes());
			lblNotes.setText(thisShortlisedAuthor.getNotes());
		} else {
			lblNotes.setText("[click to add notes]");
		}

		pneIsConferenceChair.setVisible(false);
		pneIsEditor.setVisible(false);
		pneIsExternalReviewCommittee.setVisible(false);
		pneIsGeneralChair.setVisible(false);
		pneIsProgramChair.setVisible(false);
		pneIsMisc.setVisible(false);

		if (thisShortlisedAuthor.getPosition() != null)
			switch (thisShortlisedAuthor.getPosition()) {
			case PROGRAMCHAIR:
				pneIsProgramChair.setVisible(true);
				break;
			case CONFERENCECHAIR:
				pneIsConferenceChair.setVisible(true);
				break;
			case EDITOR:
				pneIsEditor.setVisible(true);
				break;
			case EXTERNALREVIEW:
				pneIsExternalReviewCommittee.setVisible(true);
				break;
			case GENERALCHAIR:
				pneIsGeneralChair.setVisible(true);
				break;
			case MISC:
				pneIsMisc.setVisible(true);
				break;
			}

	}

}
