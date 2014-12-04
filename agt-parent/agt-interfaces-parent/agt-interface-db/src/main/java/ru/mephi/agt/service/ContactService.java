package ru.mephi.agt.service;

import ru.mephi.agt.request.ContactRequest;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.ContactListResponse;

public interface ContactService {

	public ContactListResponse getContactsFor(IdRequest idRequest);

	public BaseResponse addContactFor(ContactRequest request);

}
