package ru.mephi.agt.api;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.mephi.agt.api.request.ContactGuiRequest;
import ru.mephi.agt.api.request.IdGuiRequest;
import ru.mephi.agt.api.request.IdListGuiRequest;
import ru.mephi.agt.api.request.SendMessageGuiRequest;
import ru.mephi.agt.api.request.UserGuiRequest;
import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.ContactListResponse;
import ru.mephi.agt.response.IdListResponse;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;
import ru.mephi.agt.response.MessageListResponse;
import ru.mephi.agt.response.UserListResponse;
import ru.mephi.agt.response.UserResponse;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ApiService {

	@Path("/login")
	@POST
	public LoginResponse login(LoginRequest request);

	@Path("/register")
	@POST
	public IdResponse register(StringRequest request);

	@Path("/search")
	@POST
	public UserListResponse search(UserGuiRequest request);

	@Path("/addContact")
	@POST
	public BaseResponse addContact(ContactGuiRequest request);

	@Path("/getContactsFor")
	@POST
	public ContactListResponse getContactsFor(GuiRequest request);

	@Path("/getStatuses")
	@POST
	public IdListResponse getStatuses(IdListGuiRequest request);

	@Path("/sendMesage")
	@POST
	public BaseResponse sendMessage(SendMessageGuiRequest request);

	@Path("/receiveMessages")
	@POST
	public MessageListResponse receiveMessages(GuiRequest request);

	@Path("/receiveStoredMessages")
	@POST
	public MessageListResponse receiveStoredMessages(GuiRequest request);

	@Path("/getUserInfo")
	@POST
	public UserResponse getUserInfo(IdGuiRequest request);

	@Path("/updateSelfInfo")
	@POST
	public BaseResponse updateSelfInfo(UserGuiRequest request);
	
	@Path("/updateContact")
	@POST
	public BaseResponse updateContact(ContactGuiRequest request);

	@Path("/test")
	@POST
	public BaseResponse test(StringRequest request);

	@Path("/test2")
	@POST
	public BaseResponse test2(StringRequest request) throws NamingException;

}
