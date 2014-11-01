package ru.mephi.agt.desktop.component;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.model.Status;

public class ContactRow extends HBox {

	private ContactModel contactModel;

	public ContactRow(ContactModel contactModel) {
		super();
		this.contactModel = contactModel;
		String imagePath = null;
		if (!contactModel.isNewMessages()) {
			if (contactModel.getStatus() == Status.ONLINE) {
				imagePath = "file:resources/images/on.png";
			} else if (contactModel.getStatus() == Status.OFFLINE) {
				imagePath = "file:resources/images/off.png";
			} else {
				imagePath = "file:resources/images/unknown.png";
			}
		} else {
			imagePath = "file:resources/images/message.png";
		}

		this.getChildren().add(new ImageView(imagePath));
		this.getChildren().add(new Label(contactModel.getDisplayName()));
	}

	public ContactModel getContactModel() {
		return contactModel;
	}

}
