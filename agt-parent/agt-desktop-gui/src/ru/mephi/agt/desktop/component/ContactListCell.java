package ru.mephi.agt.desktop.component;

import javafx.scene.control.ListCell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.model.ContactModel;

public class ContactListCell extends ListCell<ContactModel> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactListCell.class);

	@Override
	protected void updateItem(ContactModel item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			setGraphic(new ContactRow(item));
		} else {
			setText(null);
			setTextFill(null);
			setGraphic(null);
		}
	}
}
