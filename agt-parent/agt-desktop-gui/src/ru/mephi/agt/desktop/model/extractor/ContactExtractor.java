package ru.mephi.agt.desktop.model.extractor;

import javafx.beans.Observable;
import javafx.util.Callback;
import ru.mephi.agt.desktop.model.ContactModel;

public class ContactExtractor implements Callback<ContactModel, Observable[]> {

	@Override
	public Observable[] call(ContactModel model) {
		return new Observable[] { model.statusProperty(),
				model.displayNameProperty(), model.newMessagesProperty() };
	}

}
