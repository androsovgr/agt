package ru.mephi.agt.desktop.component;

import javafx.scene.control.TableCell;
import ru.mephi.agt.desktop.model.UserModel;
import ru.mephi.agt.desktop.model.converter.GenderConverter;
import ru.mephi.agt.model.Gender;

public class GenderTableCell extends TableCell<UserModel, Gender> {

	private static final GenderConverter GENDER_CONVERTER = new GenderConverter();

	@Override
	protected void updateItem(Gender item, boolean empty) {
		super.updateItem(item, empty);
		if (!empty) {
			setText(GENDER_CONVERTER.toString(item));
		}
	}
}
