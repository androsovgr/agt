package ru.mephi.agt.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.model.Contact;
import ru.mephi.agt.request.ContactRequest;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.ContactListResponse;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

@Stateless
@Local(ContactService.class)
public class ContactServiceImpl implements ContactService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactServiceImpl.class);

	@Inject
	private EntityManager em;

	@Override
	public ContactListResponse getContactsFor(IdRequest request) {
		final String methodName = "getContactsFor";
		LogUtil.logStarted(LOGGER, methodName, request);
		ContactListResponse response = null;
		try {
			Session session = (Session) em.getDelegate();
			Criteria cb = session.createCriteria(Contact.class);
			cb.add(Restrictions.eq("ownerUserId", request.getId()));
			@SuppressWarnings("unchecked")
			List<Contact> contacts = cb.list();
			response = new ContactListResponse(contacts);
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new ContactListResponse(ErrorCode.INTERNAL_ERROR,
					"Can't get Contacts from DB");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public BaseResponse addContactFor(ContactRequest request) {
		final String methodName = "addContactFor";
		LogUtil.logStarted(LOGGER, methodName, request);
		BaseResponse response = null;
		try {
			em.persist(request.getContact());
			em.flush();
			response = new BaseResponse();
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR,
					"Can't add contact for");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
