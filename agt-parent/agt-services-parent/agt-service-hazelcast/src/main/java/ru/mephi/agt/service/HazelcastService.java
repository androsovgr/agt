package ru.mephi.agt.service;

import ru.mephi.agt.request.IdListRequest;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.request.gui.MessageGuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.IdListResponse;
import ru.mephi.agt.response.MessageListResponse;

public interface HazelcastService {

	public BaseResponse checkLogined(GuiRequest request);

	public BaseResponse setLogined(GuiRequest request);

	public BaseResponse addMessage(MessageGuiRequest request);

	public MessageListResponse receiveMessages(GuiRequest request);

	public IdListResponse checkOnline(IdListRequest request);

	public BaseResponse removeLogined(IdRequest request);

}
