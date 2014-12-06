package ru.mephi.agt.util;

import org.slf4j.Logger;

import ru.mephi.agt.request.BaseRequest;
import ru.mephi.agt.response.BaseResponse;

public class LogUtil {

	public static void logError(Logger LOGGER, String methodName,
			BaseRequest request, Exception e) {
		String transactionId = request != null ? request.getTransactionId()
				: null;
		LOGGER.error("[{}] Got exception while was in {}. Request: {}",
				transactionId, methodName, request, e);
	}

	public static void logStarted(Logger LOGGER, String methodName,
			BaseRequest request) {
		String transactionId = request != null ? request.getTransactionId()
				: null;
		LOGGER.debug("[{}] Method {} started", transactionId, methodName);
		LOGGER.debug("[{}] Method {} started with request {}", transactionId,
				methodName, request);
	}

	public static void logFinished(Logger LOGGER, String methodName,
			BaseRequest request, BaseResponse response) {
		String transactionId = request != null ? request.getTransactionId()
				: null;
		LOGGER.debug("[{}] Method {} finished", transactionId, methodName);
		LOGGER.debug("[{}] Method {} finished with response {}", transactionId,
				methodName, response);
	}
}
