package ru.mephi.agt.desktop.util;

import org.controlsfx.control.Notifications;

import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.util.ErrorCode;

public class ControllerUtil {

	public static boolean handleResponse(BaseResponse response) {
		if (response == null || response.getErrorCode() == null) {
			Notifications.create().title("¬нутренн€€ ошибка").showError();
			return false;
		}
		if (ErrorCode.UNAUTHORIZED == response.getErrorCode()) {
			Notifications.create().title("ќшибка авторизации").showError();
		}
		if (ErrorCode.INTERNAL_ERROR == response.getErrorCode()) {
			Notifications.create().title("¬нутренн€€ ошибка").showError();
		}
		if (!response.getErrorCode().equals(ErrorCode.OK)) {
			return false;
		}
		return true;
	}
}
