package ru.mephi.agt.service;

import ru.mephi.agt.request.MessageRequest;
import ru.mephi.agt.response.BaseResponse;

public interface MessageOrchetrationService {

	public BaseResponse sendMessage(MessageRequest request);

}
