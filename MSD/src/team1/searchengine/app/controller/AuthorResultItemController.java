package team1.searchengine.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import team1.searchengine.model.Author;
import team1.searchengine.ui.ShortlistManager;

public class AuthorResultItemController {

	HomeController homeCtrl;
	Author thisAuthor;

	@FXML
	private Hyperlink hlnkAuthorName;

	@FXML
	private Pane pneIsEditor;

	@FXML
	private Pane pneIsConferenceChair;

	@FXML
	private Pane pneIsGeneralChair;

	@FXML
	private Pane pneIsProgramChair;

	@FXML
	private Pane pneIsExternalReviewCommittee;

	@FXML
	private Pane pneIsCommitteeMember;

	@FXML
	private Pane pneIsShortlisted;

	@FXML
	private FlowPane fpneAuthorDetails;

	@FXML
	private Label lblAlias;

	public Hyperlink getNameLink() {
		return hlnkAuthorName;
	}

	/**
	 * Initialize the Author
	 * 
	 * @param homeCtrl
	 * @param author
	 */
	public void init(HomeController homeCtrl, Author author) {
		this.thisAuthor = author;
		this.homeCtrl = homeCtrl;
		this.hlnkAuthorName.setText(author.getName());
		lblAlias.setText(author.getAlias());

		if (!thisAuthor.getHasBeenEditor())
			fpneAuthorDetails.getChildren().remove(pneIsEditor);

		if (!thisAuthor.isConferenceChair())
			fpneAuthorDetails.getChildren().remove(pneIsConferenceChair);

		if (!thisAuthor.isGeneralChair())
			fpneAuthorDetails.getChildren().remove(pneIsGeneralChair);

		if (!thisAuthor.isProgramChair())
			fpneAuthorDetails.getChildren().remove(pneIsProgramChair);

		if (!thisAuthor.isInExternalReviewComittee())
			fpneAuthorDetails.getChildren().remove(pneIsExternalReviewCommittee);

		if (!thisAuthor.isCommitteeMember())
			fpneAuthorDetails.getChildren().remove(pneIsCommitteeMember);

		if (!ShortlistManager.existsInShortlist(thisAuthor))
			fpneAuthorDetails.getChildren().remove(pneIsShortlisted);
	}

	/**
	 * Handle click on the author name link
	 * 
	 * @param e
	 */
	public void onLinkClicked(ActionEvent e) {
		homeCtrl.loadDetailedView(thisAuthor);
	}
}
