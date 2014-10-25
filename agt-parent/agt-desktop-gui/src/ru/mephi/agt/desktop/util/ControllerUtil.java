package ru.mephi.agt.desktop.util;

import ru.mephi.agt._interface.request.BaseResponse;
import ru.mephi.agt.util.ErrorCode;

public class ControllerUtil {

	public static boolean handleResponse(BaseResponse response) {
		if (response == null || response.getErrorCode() == null) {
			return false;
		}
		if (!response.getErrorCode().equals(ErrorCode.OK)) {
			return false;
		}
		return true;
	}
}
