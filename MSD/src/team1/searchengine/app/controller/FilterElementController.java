package team1.searchengine.app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import team1.searchengine.ui.ContentTypeAttributeInfo;
import team1.searchengine.ui.ContentTypeFormat;
import team1.searchengine.ui.ContentTypeInfo;
import team1.searchengine.ui.Operator;

public class FilterElementController {
	HomeController homectrl;

	@FXML
	private AnchorPane apneFilterElement;

	@FXML
	private ComboBox<ContentTypeAttributeInfo> cmbFilterField;

	@FXML
	private ComboBox<Operator> cmbFilterOperator;

	@FXML
	private TextField txtFilterValue;

	@FXML
	private Hyperlink btnRemove;

	@FXML
	private AnchorPane apneRadioGroup;

	@FXML
	private RadioButton rdbnYes;

	private ContentTypeAttributeInfo selectedField;

	public Hyperlink getRemoveButton() {
		return btnRemove;
	}

	public String getFilterField() {
		if (cmbFilterField.getSelectionModel().getSelectedItem() != null)
			return cmbFilterField.getSelectionModel().getSelectedItem().getName();
		else
			return null;
	}

	public String getFilterValue() {
		if (selectedField.getFormat() == ContentTypeFormat.BOOLEAN)
			return String.valueOf(rdbnYes.isSelected());
		else
			return txtFilterValue.getText();
	}

	public Operator getOperator() {
		return this.cmbFilterOperator.getSelectionModel().getSelectedItem();
	}

	public boolean getQueriable() {
		return this.selectedField.getIsQueryable();
	}

	public void init(ContentTypeInfo ct, HomeController homeCtrl) {
		this.homectrl = homeCtrl;

		ObservableList<ContentTypeAttributeInfo> fields = FXCollections.observableArrayList(ct.getAttributes());
		cmbFilterField.setItems(fields);
		cmbFilterField.getSelectionModel().selectFirst();

		cmbFilterField.setCellFactory(combobox -> {
			return new ListCell<ContentTypeAttributeInfo>() {
				@Override
				protected void updateItem(ContentTypeAttributeInfo item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getDisplayName().toUpperCase());
					}
				}
			};
		});

		// render drop down selected item
		cmbFilterField.setConverter(new StringConverter<ContentTypeAttributeInfo>() {

			@Override
			public String toString(ContentTypeAttributeInfo ct) {
				if (ct == null) {
					return null;
				} else {
					return ct.getDisplayName().toUpperCase();
				}
			}

			@Override
			public ContentTypeAttributeInfo fromString(String arg0) {
				return null;
			}
		});
		onFieldSelected(null);

		txtFilterValue.textProperty().addListener((observable, oldValue, newValue) -> {
			if (selectedField.getFormat() == ContentTypeFormat.NUMBER) {
				newValue = newValue.replaceAll("[^0-9]", "");

				if (newValue.length() > 3)
					newValue = newValue.substring(0, 4);

				txtFilterValue.setText(newValue);
			}
		});
	}

	public void onFieldSelected(ActionEvent e) {
		this.selectedField = cmbFilterField.getSelectionModel().getSelectedItem();
		cmbFilterOperator.setItems(FXCollections.observableArrayList(selectedField.getOperators()));
		cmbFilterOperator.getSelectionModel().selectFirst();

		txtFilterValue.setVisible(selectedField.getFormat() != ContentTypeFormat.BOOLEAN);
		apneRadioGroup.setVisible(!txtFilterValue.isVisible());

		if (selectedField.getFormat() == ContentTypeFormat.BOOLEAN) {
			txtFilterValue.setVisible(false);
			apneRadioGroup.setVisible(true);
			cmbFilterOperator.getSelectionModel().select(Operator.EQ);
			cmbFilterOperator.setDisable(true);
		} else {
			txtFilterValue.setVisible(true);
			apneRadioGroup.setVisible(false);
			cmbFilterOperator.setDisable(false);
		}

		cmbFilterOperator.setCellFactory(combobox -> {
			return new ListCell<Operator>() {
				@Override
				protected void updateItem(Operator item, boolean empty) {
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
		cmbFilterOperator.setConverter(new StringConverter<Operator>() {
			@Override
			public String toString(Operator op) {
				if (op == null) {
					return null;
				} else {
					return op.toString();
				}
			}

			@Override
			public Operator fromString(String arg0) {
				return null;
			}
		});
	}

	public void onKeyTyped(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			homectrl.onSearchButtonClicked(null);
		}
	}
}
