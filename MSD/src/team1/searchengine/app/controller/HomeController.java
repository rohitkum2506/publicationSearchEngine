package team1.searchengine.app.controller;

import java.awt.Desktop;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import team1.searchengine.app.AppMain;
import team1.searchengine.common.Query;
import team1.searchengine.common.QueryElement;
import team1.searchengine.database.preProcessor.ComitteePopulator;
import team1.searchengine.exceptions.InvalidQueryException;
import team1.searchengine.logging.Logger;
import team1.searchengine.model.*;
import team1.searchengine.queryengine.QueryEngineProviderService;
import team1.searchengine.ui.*;
import team1.searchengine.ui.ContentType;

public class HomeController implements DetailedViewVisitor {
	class FilterElementNode {
		private FilterElementController controller;
		private Node node;

		public FilterElementNode(FilterElementController controller, Node node) {
			this.controller = controller;
			this.node = node;
		}

		public FilterElementController getController() {
			return this.controller;
		}

		public Node getNode() {
			return this.node;
		}
	}

	private final String logLevel = "UI-ERROR";
	private static final String defaultSearchIndex = "dblp-test";
	private VBox currentView;
	private ArrayList<VBox> history = new ArrayList<>();
	private ContentTypeInfo selectedContentType;
	private ArrayList<FilterElementNode> filters = new ArrayList<>();

	@FXML
	private ComboBox<ContentTypeInfo> cmbContentType;

	@FXML
	private GridPane grdFilterWrapper;

	@FXML
	private ScrollPane spneFilterScrollWrapper;

	@FXML
	private ScrollPane spneSideContainer;

	@FXML
	private TextField txtDefaultSearchBox;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnShowShortlist;

	@FXML
	private GridPane grdMainContainer;

	@FXML
	private ProgressIndicator prgMainProgressBar;

	@FXML
	private ProgressIndicator prgSideProgressBar;

	@FXML
	private FlowPane fpneMainAltPane;

	@FXML
	private FlowPane fpneSideAltPane;

	@FXML
	private AnchorPane apneSideContainer;

	@FXML
	private Label lblError;

	@FXML
	private Label lblMessage;

	@FXML
	private VBox vboxWelcomeScreen;

	@FXML
	private Hyperlink hlnkBack;

	/**
	 * handle content type change
	 * 
	 * @param selectedCT
	 */
	private void onContentTypeChanged(ContentTypeInfo selectedCT) {
		this.selectedContentType = selectedCT;
		filters.clear();
		addFilter();
	}

	/**
	 * add filter element
	 */
	private void addFilter() {
		try {
			// load Filter Element
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/FilterElementView.fxml"));
			AnchorPane filterElement = loader.load();
			FilterElementController filterElementCtrl = loader.getController();
			FilterElementNode thisFilterNode = new FilterElementNode(filterElementCtrl, filterElement);

			filterElementCtrl.init(selectedContentType, this);

			// hook up remove button
			Hyperlink filterRemoveButton = filterElementCtrl.getRemoveButton();
			filterRemoveButton.setOnAction(event -> {
				filters.remove(thisFilterNode);
				updateFilterGrid();
			});

			// add to filters collection
			filters.add(0, thisFilterNode);
			updateFilterGrid();

		} catch (Exception ex) {
			Logger.log("Failed to load filter element" + ex.getMessage(), logLevel);
			setError("Oops! Error adding filter. " + ex.getMessage());
		}
	}

	/**
	 * refresh filter grid container
	 */
	private void updateFilterGrid() {
		grdFilterWrapper.getChildren().clear();

		for (int i = 0; i < filters.size(); i++) {
			grdFilterWrapper.addRow(i, filters.get(i).node);
		}
		// scroll to top of filter container
		spneFilterScrollWrapper.setVvalue(0.0f);
	}

	/**
	 * create query query based on the selected filers
	 * 
	 * @return Query
	 */
	private Query createQuery() {
		Query thisQuery = new Query(selectedContentType.getName().name().toLowerCase());
		// iterate through the filter controllers and form filter elements
		filters.forEach(thisFilter -> {
			if (thisFilter.getController().getQueriable()) {
				String field = thisFilter.getController().getFilterField();
				Operator operator = thisFilter.getController().getOperator();
				String value = thisFilter.getController().getFilterValue();
				boolean isNested = field.equals("Author") || field.equals("editors");
				if (field.equals("Author")) {
					field = "authors";
				}
				if (field != null && operator != null) {
					thisQuery.addQueryElement(new QueryElement(value, field, operator, isNested));
				}
			}
		});
		return thisQuery;
	}

	/**
	 * create query query based on the selected filers
	 * 
	 * @return Query
	 */
	private ArrayList<QueryElement> createUIQuery() {
		ArrayList<QueryElement> qes = new ArrayList<>();
		// iterate through the filter controllers and form filter elements
		filters.forEach(thisFilter -> {
			if (!thisFilter.getController().getQueriable()) {
				String field = thisFilter.getController().getFilterField();
				Operator operator = thisFilter.getController().getOperator();
				String value = thisFilter.getController().getFilterValue();
				if (field != null && operator != null)
					qes.add(new QueryElement(value, field, operator, false));
			}
		});
		return qes;
	}

	/**
	 * Check if at least one valid filter is available
	 * 
	 * @return
	 */
	private boolean checkForValidQuery() {
		for (int i = 0; i < filters.size(); i++) {
			if (!filters.get(i).getController().getFilterValue().isEmpty())
				return true;
		}
		return false;

	}

	/**
	 * show / hide side loading screen
	 * 
	 * @param show
	 */
	private void toggleSideLoadingScreen(boolean show) {
		fpneSideAltPane.setVisible(show);
		prgSideProgressBar.setVisible(show);
	}

	/**
	 * show / hide main loading screen
	 * 
	 * @param show
	 */
	private void toggleMainLoadingScreen(boolean show) {
		fpneMainAltPane.setVisible(show);
		prgMainProgressBar.setVisible(show);
	}

	private void toggleWelcomeScreen(boolean show) {
		vboxWelcomeScreen.setVisible(show);
	}

	/**
	 * Initialize Home controller and load the home layout
	 */
	public void init() {
		ObservableList<ContentTypeInfo> contentTypesObservableList = FXCollections
				.observableArrayList(UIManager.getAllContentTypes());
		cmbContentType.setItems(contentTypesObservableList);
		cmbContentType.getSelectionModel().selectFirst();
		selectedContentType = cmbContentType.getSelectionModel().getSelectedItem();
		onContentTypeChanged(selectedContentType);

		// render drop down for content types
		cmbContentType.setCellFactory((combobox) -> {
			return new ListCell<ContentTypeInfo>() {
				@Override
				protected void updateItem(ContentTypeInfo item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getName().name().toUpperCase());
					}
				}
			};
		});

		// render drop down selected item
		cmbContentType.setConverter(new StringConverter<ContentTypeInfo>() {

			@Override
			public String toString(ContentTypeInfo ct) {
				if (ct == null) {
					return null;
				} else {
					return ct.getName().name();
				}
			}

			@Override
			public ContentTypeInfo fromString(String arg0) {
				return null;
			}
		});

		// Handle ComboBox event.
		cmbContentType.setOnAction((event) -> {
			ContentTypeInfo selectedCt = cmbContentType.getSelectionModel().getSelectedItem();
			onContentTypeChanged(selectedCt);
		});

		updateShortlistCount();
	}

	/**
	 * handle add filter event
	 * 
	 * @param e
	 */
	public void onAddFilterButtonClicked(ActionEvent e) {
		addFilter();
	}

	/**
	 * handle search button click
	 * 
	 * @param e
	 */
	public void onSearchButtonClicked(ActionEvent e) {

		clearError();

		if (!checkForValidQuery()) {
			setError("Please specify a valid filter");
			return;

		}
		toggleMainLoadingScreen(true);
		toggleWelcomeScreen(false);
		// run task in new background thread
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Query q = createQuery();
				ArrayList<Query> qs = new ArrayList<>();

				if (selectedContentType.getName() == ContentType.PUBLICATION) {
					q.setContentType("JournalPublication");
					Query qConf = new Query("conferencePublication");
					qConf.setQueryElements(q.getQueryElements());
					qs.add(qConf);
				}

				qs.add(q);
				ArrayList<QueryElement> uiQueries = createUIQuery();

				PublicationEntityCollection pebc = new PublicationEntityCollection();

				QueryEngineProviderService queryEngine = new QueryEngineProviderService();
				for (int i = 0; i < qs.size(); i++) {
					try {

						if (!q.getQueryElements().isEmpty()) {
							Logger.log("Firing ES for query : " + qs.toString(), "info");
							PublicationEntityCollection thisResult = queryEngine.executeQuery(defaultSearchIndex,
									qs.get(i), 250, 0);
							Logger.log("ES query complete : ", "info");
							Logger.log("----------------------------------------------------------", "");
							thisResult = filterDuplicates(thisResult);
							
							if (thisResult != null) {
								pebc.getAuthors().addAll(thisResult.getAuthors());
								pebc.getJournalPublications().addAll(thisResult.getJournalPublications());
								pebc.getConferencePublications().addAll(thisResult.getConferencePublications());
								pebc.getJournals().addAll(thisResult.getJournals());
								pebc.getConferences().addAll(thisResult.getConferences());
							}
							pebc = filterByCommitteePosition(pebc, uiQueries);
						} else {
							ArrayList<String> addedAuthorNames = new ArrayList<>();
							for (String authorName : ComitteePopulator.committeeData.values()) {
								String[] authorNames = authorName.split(",");
								for (String thisAuthor : authorNames) {
									if (addedAuthorNames.contains(thisAuthor))
										continue;
									pebc.getAuthors().add(new Author(thisAuthor, "", "", false, false));
									addedAuthorNames.add(thisAuthor);
								}
							}

							pebc = filterByCommitteePosition(pebc, uiQueries);
							Collections.sort(pebc.getAuthors(), (a, b) -> a.getRawName().compareTo(b.getRawName()));
						}

					} catch (InvalidQueryException ex) {
						lblMessage.setText("Invalid query. " + ex.getMessage());
						Logger.log("Invalid Query for : " + q.toString(), "ERROR");
						ex.printStackTrace();
					}
				}

				ArrayList<Node> i = buildResult(pebc);

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (!i.isEmpty()) {
							showResults(i);

						} else {
							lblMessage.setText("No results to show");
							toggleWelcomeScreen(true);
						}
						toggleMainLoadingScreen(false);
					}
				});
				return null;
			}
		};
		new Thread(task).start();
	}

	/**
	 * Build UI nodes from search results
	 * 
	 * @param results
	 * @return
	 */
	public ArrayList<Node> buildResult(PublicationEntityCollection results) {
		ArrayList<Node> items = new ArrayList<>();

		try {

			// AUTHORS:
			if (results.getAuthors() != null && results.getAuthors().size() > 0) {
				// section title
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/ResultSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				ResultSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("AUTHORS");
				items.add(sectionTitleElement);

				for (int i = 0; i < results.getAuthors().size(); i++) {
					try {
						FXMLLoader thisLoader = new FXMLLoader();
						thisLoader.setLocation(AppMain.class.getResource("view/AuthorResultItemView.fxml"));
						VBox authorResultItem = thisLoader.load();
						AuthorResultItemController authorResItemCtrl = thisLoader.getController();
						authorResItemCtrl.init(this, results.getAuthors().get(i));
						items.add(authorResultItem);
					} catch (Exception ex) {
						Logger.log("Error loading Author result view item. " + ex.getMessage(), logLevel);
						setError("Error loading results. " + ex.getMessage());
					}
				}
			}

			// JOURNAL PUBLICATIONS
			if (results.getJournalPublications() != null && results.getJournalPublications().size() > 0) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/ResultSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				ResultSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("JOURNAL PUBLICATIONS");
				items.add(sectionTitleElement);

				for (int i = 0; i < results.getJournalPublications().size(); i++) {
					try {
						FXMLLoader thisLoader = new FXMLLoader();
						thisLoader.setLocation(AppMain.class.getResource("view/JournalPublicationResultItemView.fxml"));
						VBox jPubResultItem = thisLoader.load();
						JournalPublicationResultItemController jPubResItemCtrl = thisLoader.getController();
						jPubResItemCtrl.init(this, results.getJournalPublications().get(i));
						items.add(jPubResultItem);
					} catch (Exception ex) {
						Logger.log("Error loading Journal Publication result view item. " + ex.getMessage(), logLevel);
						setError("Error loading results. " + ex.getMessage());
					}
				}
			}

			// CONFERENCE PUBLICATIONS
			if (results.getConferencePublications() != null && !results.getConferencePublications().isEmpty()) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/ResultSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				ResultSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("CONFERENCE PUBLICATIONS");
				items.add(sectionTitleElement);

				for (int i = 0; i < results.getConferencePublications().size(); i++) {
					try {
						FXMLLoader thisLoader = new FXMLLoader();
						thisLoader.setLocation(
								AppMain.class.getResource("view/ConferencePublicationResultItemView.fxml"));
						VBox cPubResultItem = thisLoader.load();
						ConferencePublicationResultItemController cPubResItemCtrl = thisLoader.getController();
						cPubResItemCtrl.init(this, results.getConferencePublications().get(i));
						items.add(cPubResultItem);
					} catch (Exception ex) {
						Logger.log("Error loading Conference Publication result view item. " + ex.getMessage(),
								logLevel);
						setError("Error loading results. " + ex.getMessage());
					}
				}
			}

			// Journals
			if (results.getJournals() != null && results.getJournals().size() > 0) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/ResultSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				ResultSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("JOURNALS");
				items.add(sectionTitleElement);

				for (int i = 0; i < results.getJournals().size(); i++) {
					try {
						FXMLLoader thisLoader = new FXMLLoader();
						thisLoader.setLocation(AppMain.class.getResource("view/ProceedingResultItemView.fxml"));
						VBox proceedingResultItem = thisLoader.load();
						ProceedingResultItemController proceedingResItemCtrl = thisLoader.getController();
						proceedingResItemCtrl.init(this, results.getJournals().get(i));
						items.add(proceedingResultItem);
					} catch (Exception ex) {
						Logger.log("Error loading Journal result view item. " + ex.getMessage(), logLevel);
						setError("Error loading results. " + ex.getMessage());
					}
				}
			}
			// Conferences
			if (results.getConferences() != null && results.getConferences().size() > 0) {
				FXMLLoader sectionTitleLoader = new FXMLLoader();
				sectionTitleLoader.setLocation(AppMain.class.getResource("view/ResultSectionTitleView.fxml"));
				AnchorPane sectionTitleElement = sectionTitleLoader.load();
				ResultSectionTitleController sectionTitleController = sectionTitleLoader.getController();
				sectionTitleController.setTitle("CONFERENCES");
				items.add(sectionTitleElement);

				for (int i = 0; i < results.getConferences().size(); i++) {
					try {
						FXMLLoader thisLoader = new FXMLLoader();
						thisLoader.setLocation(AppMain.class.getResource("view/ProceedingResultItemView.fxml"));
						VBox proceedingResultItem = thisLoader.load();
						ProceedingResultItemController proceedingResItemCtrl = thisLoader.getController();
						proceedingResItemCtrl.init(this, results.getConferences().get(i));
						items.add(proceedingResultItem);
					} catch (Exception ex) {
						Logger.log("Error loading proceeding result item view. " + ex.getMessage(), logLevel);
						setError("Error loading results. " + ex.getMessage());
					}
				}
			}

		} catch (Exception ex) {
			Logger.log("Failed to load result containter" + ex.getMessage(), logLevel);
			setError("Error loading results. " + ex.getMessage());
		}

		return items;
	}

	/**
	 * Show results in UI
	 * 
	 * @param items
	 */
	public void showResults(ArrayList<Node> items) {
		grdMainContainer.getChildren().clear();
		for (int i = 0; i < items.size(); i++) {
			grdMainContainer.addRow(i, items.get(i));
		}
	}

	/**
	 * Retrieve details of the selected author such as his publications,
	 * connections etc and display in preview pane
	 * 
	 * @param author
	 */
	public void loadDetailedView(Author author) {
		toggleSideLoadingScreen(true);
		// run task in new background thread
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// get all journal publications of the author
				Query journalPublicationQuery = new Query("JournalPublication");
				journalPublicationQuery
						.addQueryElement(new QueryElement(author.getName(), "authors", Operator.EQ, true));
				QueryEngineProviderService qe = new QueryEngineProviderService();
				PublicationEntityCollection authorJPubs = qe.executeQuery(defaultSearchIndex, journalPublicationQuery,
						250, 0);
				authorJPubs = filterDuplicates(authorJPubs);

				// get all conference publications of the author
				Query conferencePublicationQuery = new Query("conferencePublication");
				conferencePublicationQuery
						.addQueryElement(new QueryElement(author.getName(), "authors", Operator.EQ, true));
				PublicationEntityCollection authorCPubs = qe.executeQuery(defaultSearchIndex, journalPublicationQuery,
						250, 0);
				authorCPubs = filterDuplicates(authorCPubs);

				// merge into a single collection
				PublicationEntityCollection authorPubs = new PublicationEntityCollection();
				authorPubs.setJournalPublication(authorJPubs.getJournalPublications());
				authorPubs.setConferencePublication(authorCPubs.getConferencePublications());

				// get all conference where the author has been an editor
				Query editorConfQuery = new Query(ContentType.CONFERENCE.name().toLowerCase());
				editorConfQuery.addQueryElement(new QueryElement(author.getName(), "editors", Operator.EQ, true));
				PublicationEntityCollection editorConf = qe.executeQuery(defaultSearchIndex, editorConfQuery, 250, 0);

				// get all journals where the author has been an editor
				Query editorJournalQuery = new Query(ContentType.JOURNAL.name().toLowerCase());
				editorJournalQuery.addQueryElement(new QueryElement(author.getName(), "editors", Operator.EQ, true));
				PublicationEntityCollection editorJournals = qe.executeQuery(defaultSearchIndex, editorJournalQuery,
						250, 0);

				PublicationEntityCollection edits = new PublicationEntityCollection();
				edits.setConferences(editorConf.getConferences());
				edits.setJournals(editorJournals.getJournals());
				PublicationEntityCollection editorPubs = filterDuplicates(edits);

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						loadAuthorView(author, authorPubs, editorPubs);
						toggleSideLoadingScreen(false);
					}
				});
				return null;
			}
		};
		new Thread(task).start();
	}

	/**
	 * Display detailed info about the selected author in the display pane
	 * 
	 * @param a
	 * @param pubs
	 * @param edits
	 */
	private void loadAuthorView(Author a, PublicationEntityCollection pubs, PublicationEntityCollection edits) {
		try {
			FXMLLoader thisLoader = new FXMLLoader();
			thisLoader.setLocation(AppMain.class.getResource("view/AuthorDetailedView.fxml"));
			VBox authorDetailedView = thisLoader.load();
			AuthorDetailedController authorDetailedCtrl = thisLoader.getController();
			authorDetailedCtrl.init(a, pubs, edits, this);
			AnchorPane.setTopAnchor(authorDetailedView, 0.0);
			AnchorPane.setBottomAnchor(authorDetailedView, 0.0);
			AnchorPane.setLeftAnchor(authorDetailedView, 0.0);
			AnchorPane.setRightAnchor(authorDetailedView, 0.0);
			apneSideContainer.getChildren().clear();
			apneSideContainer.getChildren().add(authorDetailedView);
			addToHistory(authorDetailedView);

		} catch (Exception ex) {
			Logger.log("Error loading author detailed view. " + ex.getMessage(), logLevel);
			setError("Error loading results. " + ex.getMessage());
		}
	}

	/**
	 * Retrieve details of the given journal
	 * 
	 * @param journal
	 */
	public void loadDetailedView(Journal journal) {
		toggleSideLoadingScreen(true);
		// run task in new background thread
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// get all publications of the journal
				Query journalQuery = new Query("JournalPublication");
				journalQuery.addQueryElement(new QueryElement(journal.getTitle(), "journal", Operator.EQ, true));

				QueryEngineProviderService qe = new QueryEngineProviderService();
				PublicationEntityCollection result = filterDuplicates(
						qe.executeQuery(defaultSearchIndex, journalQuery, 250, 0));

				journal.setPublications(result.getJournalPublications());
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						loadJournalView(journal);
						toggleSideLoadingScreen(false);
					}
				});
				return null;
			}
		};
		new Thread(task).start();
	}

	/**
	 * Load details of the given journal in the side bar
	 * 
	 * @param thisJournal
	 */
	private void loadJournalView(Journal thisJournal) {
		try {
			FXMLLoader thisLoader = new FXMLLoader();
			thisLoader.setLocation(AppMain.class.getResource("view/JournalProceedingDetailedView.fxml"));
			VBox journalDetailedView = thisLoader.load();
			JournalProceedingDetailedController journalDetailedCtrl = thisLoader.getController();
			journalDetailedCtrl.init(thisJournal, this);
			AnchorPane.setTopAnchor(journalDetailedView, 0.0);
			AnchorPane.setBottomAnchor(journalDetailedView, 0.0);
			AnchorPane.setLeftAnchor(journalDetailedView, 0.0);
			AnchorPane.setRightAnchor(journalDetailedView, 0.0);
			apneSideContainer.getChildren().clear();
			apneSideContainer.getChildren().add(journalDetailedView);
			addToHistory(journalDetailedView);
		} catch (Exception ex) {
			Logger.log("Error loading Journal detailed view. " + ex.getMessage(), logLevel);
			setError("Error loading journal details. " + ex.getMessage());
		}
	}

	/**
	 * Retrieve details of the given conference
	 * 
	 * @param thisConference
	 */
	public void loadDetailedView(Conference thisConference) {
		toggleSideLoadingScreen(true);
		// run task in new background thread
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// get all publications of the journal
				Query conferenceQuery = new Query("conferencePublication");
				conferenceQuery.addQueryElement(new QueryElement(thisConference.getKey(), "key", Operator.EQ, true));

				QueryEngineProviderService qe = new QueryEngineProviderService();
				PublicationEntityCollection result = filterDuplicates(
						qe.executeQuery(defaultSearchIndex, conferenceQuery, 250, 0));

				thisConference.setPublications(result.getConferencePublications());

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						loadConferenceView(thisConference);
						toggleSideLoadingScreen(false);
					}
				});
				return null;
			}
		};
		new Thread(task).start();
	}

	/**
	 * Load details of the given conference in the side bar
	 * 
	 * @param thisJournal
	 */
	private void loadConferenceView(Conference thisConference) {
		try {
			FXMLLoader thisLoader = new FXMLLoader();
			thisLoader.setLocation(AppMain.class.getResource("view/ConferenceProceedingDetailedView.fxml"));
			VBox conferenceDetailedView = thisLoader.load();
			ConferenceProceedingDetailedController conferenceDetailedCtrl = thisLoader.getController();
			conferenceDetailedCtrl.init(thisConference, this);
			AnchorPane.setTopAnchor(conferenceDetailedView, 0.0);
			AnchorPane.setBottomAnchor(conferenceDetailedView, 0.0);
			AnchorPane.setLeftAnchor(conferenceDetailedView, 0.0);
			AnchorPane.setRightAnchor(conferenceDetailedView, 0.0);
			apneSideContainer.getChildren().clear();
			apneSideContainer.getChildren().add(conferenceDetailedView);
			addToHistory(conferenceDetailedView);
		} catch (Exception ex) {
			Logger.log("Error loading Conference detailed view. " + ex.getMessage(), logLevel);
			setError("Error loading conference details. " + ex.getMessage());
		}
	}

	/**
	 * Open a detailed view of a Journal or Conference
	 * 
	 * @param proceeding
	 */
	public void loadDetailedView(GenericProceeding proceeding) {
		toggleSideLoadingScreen(true);
		if (proceeding instanceof Journal) {
			loadDetailedView((Journal) proceeding);
		} else
			loadDetailedView((Conference) proceeding);
	}

	/**
	 * Open a detailed view of a Journal Publication
	 * 
	 * @param journalPub
	 */
	public void loadDetailedView(JournalPublication journalPub) {
		try {
			toggleSideLoadingScreen(true);
			FXMLLoader thisLoader = new FXMLLoader();
			thisLoader.setLocation(AppMain.class.getResource("view/JournalPublicationDetailedView.fxml"));
			VBox journalPubDetailedView = thisLoader.load();
			JournalPublicationDetailedController journalPubDetailedCtrl = thisLoader.getController();
			journalPubDetailedCtrl.init(journalPub, this);
			AnchorPane.setTopAnchor(journalPubDetailedView, 0.0);
			AnchorPane.setBottomAnchor(journalPubDetailedView, 0.0);
			AnchorPane.setLeftAnchor(journalPubDetailedView, 0.0);
			AnchorPane.setRightAnchor(journalPubDetailedView, 0.0);
			apneSideContainer.getChildren().clear();
			apneSideContainer.getChildren().add(journalPubDetailedView);
			addToHistory(journalPubDetailedView);
			toggleSideLoadingScreen(false);
		} catch (Exception ex) {
			Logger.log("Error loading Journal Publication detailed view. " + ex.getMessage(), logLevel);
			setError("Error loading journal publication details. " + ex.getMessage());
		}
	}

	// load details of Conference Publication in view pane
	public void loadDetailedView(ConferencePublication conferencePublication) {
		toggleSideLoadingScreen(true);
		// run task in new background thread
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// get all publications of the journal
				Query conferenceQuery = new Query("conference");
				conferenceQuery.addQueryElement(
						new QueryElement(conferencePublication.getConference().getKey(), "key", Operator.EQ, false));

				QueryEngineProviderService qe = new QueryEngineProviderService();
				PublicationEntityCollection result = filterDuplicates(
						qe.executeQuery(defaultSearchIndex, conferenceQuery, 1, 0));

				if (result.getConferences() != null && !result.getConferences().isEmpty())
					conferencePublication.setConference(result.getConferences().get(0));

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						loadConferencePublicationView(conferencePublication);
						toggleSideLoadingScreen(false);
					}
				});
				return null;
			}
		};
		new Thread(task).start();
	}

	private void loadConferencePublicationView(ConferencePublication thisConferencePublication) {
		try {

			FXMLLoader thisLoader = new FXMLLoader();
			thisLoader.setLocation(AppMain.class.getResource("view/ConferencePublicationDetailedView.fxml"));
			VBox conferencePubDetailedView = thisLoader.load();
			ConferencePublicationDetailedController conferencePubDetailedCtrl = thisLoader.getController();
			conferencePubDetailedCtrl.init(thisConferencePublication, this);
			AnchorPane.setTopAnchor(conferencePubDetailedView, 0.0);
			AnchorPane.setBottomAnchor(conferencePubDetailedView, 0.0);
			AnchorPane.setLeftAnchor(conferencePubDetailedView, 0.0);
			AnchorPane.setRightAnchor(conferencePubDetailedView, 0.0);
			apneSideContainer.getChildren().clear();
			apneSideContainer.getChildren().add(conferencePubDetailedView);
			addToHistory(conferencePubDetailedView);
		} catch (Exception ex) {
			Logger.log("Error loading Conference Publication detailed view. " + ex.getMessage(), logLevel);
			setError("Error loading conference publication details. " + ex.getMessage());
		}
	}

	/**
	 * Open URL in default browser
	 * 
	 * @param url
	 */
	public void openUrl(String url) {
		if (url == null)
			return;
		try {
			if (!url.contains("http"))
				url = "http://dblp.uni-trier.de/" + url;
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch (Exception ex) {
			Logger.log("Error opening URL. " + ex.getMessage(), url);
			setError("Error opening URL. " + ex.getMessage());
		}
	}

	/**
	 * Back button clicked. Load previous screen on side display pane
	 * 
	 * @param e
	 */
	public void onBackButtonClicked(ActionEvent e) {
		VBox thisHistory = getFromHistory();
		if (thisHistory != null) {
			apneSideContainer.getChildren().clear();
			apneSideContainer.getChildren().add(thisHistory);
		}
	}

	/**
	 * Add the current UI container to history
	 * 
	 * @param v
	 */
	public void addToHistory(VBox v) {
		if (history.size() >= 15) {
			history.remove(0);
		}
		history.add(v);
		this.currentView = v;
		hlnkBack.setDisable(history.size() < 2);
	}

	/**
	 * Get the last UI container from history
	 * 
	 * @return
	 */
	public VBox getFromHistory() {
		VBox thisHistory = null;
		if (!history.isEmpty())
			thisHistory = history.remove(history.size() - 1);
		if (history.isEmpty())
			hlnkBack.setDisable(true);
		if (thisHistory == currentView)
			return getFromHistory();
		return thisHistory;
	}

	/**
	 * Update shortlist count on button
	 */
	public void updateShortlistCount() {
		int shortlistCount = ShortlistManager.getShortlist().size();
		if (shortlistCount > 0)
			btnShowShortlist.setText("[" + shortlistCount + "]");
		else
			btnShowShortlist.setText("");
	}

	/**
	 * Show shortlist in the display pane
	 * 
	 * @param e
	 */
	public void onShortListClick(ActionEvent e) {
		try {
			toggleSideLoadingScreen(false);
			FXMLLoader thisLoader = new FXMLLoader();
			thisLoader.setLocation(AppMain.class.getResource("view/ShortlistView.fxml"));
			VBox shotlistView = thisLoader.load();
			ShortlistController shortlistCtrl = thisLoader.getController();
			shortlistCtrl.init(this);
			AnchorPane.setTopAnchor(shotlistView, 0.0);
			AnchorPane.setBottomAnchor(shotlistView, 0.0);
			AnchorPane.setLeftAnchor(shotlistView, 0.0);
			AnchorPane.setRightAnchor(shotlistView, 0.0);
			apneSideContainer.getChildren().clear();
			apneSideContainer.getChildren().add(shotlistView);
		} catch (Exception ex) {
			System.out.println("Error loading shortlist view");
			ex.printStackTrace();
			setError("Error loading shortlist view. " + ex.getMessage());
		}
	}

	/**
	 * remove duplicate publication entity items
	 * 
	 * @param e
	 * @return
	 */
	public PublicationEntityCollection filterDuplicates(PublicationEntityCollection e) {
		if (e == null)
			return null;
		PublicationEntityCollection filtered = new PublicationEntityCollection();

		if (e.getAuthors() != null) {
			ArrayList<String> addedAuthorNames = new ArrayList<>();
			ArrayList<Author> filteredAuthors = new ArrayList<>();
			for (Author thisAuthor : e.getAuthors()) {
				if (addedAuthorNames.contains(thisAuthor.getName().trim()) || thisAuthor.getName().length() < 3)
					continue;
				filteredAuthors.add(thisAuthor);
				addedAuthorNames.add(thisAuthor.getName().trim());
			}
			filtered.setAuthors(filteredAuthors);
		}

		if (e.getJournals() != null) {
			e.getJournals().forEach(a -> {
				if (filtered.getJournals().stream().filter(i -> a.getTitle().equals(i.getTitle())).count() == 0) {
					filtered.getJournals().add(a);
				}
			});
		}

		if (e.getConferences() != null) {
			e.getConferences().forEach(a -> {
				if (filtered.getConferences().stream().filter(i -> a.getTitle().equals(i.getTitle())).count() == 0) {
					filtered.getConferences().add(a);
				}
			});
		}

		if (e.getConferencePublications() != null) {
			e.getConferencePublications().forEach(a -> {
				if (filtered.getConferencePublications().stream().filter(i -> a.getTitle().equals(i.getTitle()))
						.count() == 0) {
					filtered.getConferencePublications().add(a);
				}
			});
		}

		if (e.getJournalPublications() != null) {
			e.getJournalPublications().forEach(a -> {
				if (filtered.getJournalPublications().stream().filter(i -> a.getTitle().equals(i.getTitle()))
						.count() == 0) {
					filtered.getJournalPublications().add(a);
				}
			});
		}

		return filtered;
	}

	private PublicationEntityCollection filterByCommitteePosition(PublicationEntityCollection pebc,
			ArrayList<QueryElement> qes) {
		if (pebc.getAuthors() != null) {
			for (int i = 0; i < qes.size(); i++) {
				for (int j = 0; j < pebc.getAuthors().size(); j++) {
					boolean match = ComitteePopulator.Match(pebc.getAuthors().get(j).getName(), qes.get(i).getField());
					if ((qes.get(i).getKeyword().equals("true") && !match)
							|| (qes.get(i).getKeyword().equals("false") && match)) {
						pebc.getAuthors().remove(j);
						j--;
					}
				}
			}
		}
		return pebc;
	}

	/**
	 * Set error message on the UI
	 * 
	 * @param error
	 *            message to be shown
	 */
	public void setError(String error) {
		lblError.setText(error);
		lblError.setVisible(true);
	}

	/**
	 * clear the error message in the UI
	 */
	public void clearError() {
		lblError.setText("");
		lblError.setVisible(false);
	}
}
