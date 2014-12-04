package ru.mephi.agt.service;

import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.MessageListRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.MessageListResponse;


public interface MessageService {

	public BaseResponse storeMessages(MessageListRequest request);

	public MessageListResponse getMessages(IdRequest request);
}
