package ru.mephi.agt.desktop.model.bean;

import javafx.scene.control.Tab;
import ru.mephi.agt.desktop.view.MessagesController;

public class MessageTabControllerBean {

	private Tab tab;
	private MessagesController controller;

	public MessageTabControllerBean(Tab tab, MessagesController controller) {
		if (tab == null) {
			throw new IllegalArgumentException("Param tab can't be null");
		}
		if (controller == null) {
			throw new IllegalArgumentException("Param controller can't be null");
		}
		this.tab = tab;
		this.controller = controller;
	}

	public Tab getTab() {
		return tab;
	}

	public MessagesController getController() {
		return controller;
	}

}
