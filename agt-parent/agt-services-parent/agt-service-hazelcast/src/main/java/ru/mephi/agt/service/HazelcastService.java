package ru.mephi.agt.service;

import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.request.gui.MessageGuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.MessageListResponse;

public interface HazelcastService {

	public BaseResponse checkLogined(GuiRequest request);

	public BaseResponse setLogined(GuiRequest request);

	public BaseResponse addMessage(MessageGuiRequest request);

	public MessageListResponse receiveMessages(GuiRequest request);
}
