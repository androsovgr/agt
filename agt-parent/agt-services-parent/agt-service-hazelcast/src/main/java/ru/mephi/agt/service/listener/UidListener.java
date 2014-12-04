package ru.mephi.agt.service.listener;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.ObjectRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.service.LoginService;
import ru.mephi.agt.util.LogUtil;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.ISet;
import com.hazelcast.core.MapEvent;

public class UidListener implements EntryListener<Long, String> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UidListener.class);

	private ISet<Long> userSet;

	@Override
	public void entryAdded(EntryEvent<Long, String> event) {
	}

	@Override
	public void entryRemoved(EntryEvent<Long, String> event) {
	}

	@Override
	public void entryUpdated(EntryEvent<Long, String> event) {
	}

	@Override
	public void entryEvicted(EntryEvent<Long, String> event) {
		String methodName = "entryEvicted";
		ObjectRequest request = new ObjectRequest(event);
		BaseResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			String url = "java:global/agt-ear/agt-service-login/LoginServiceImpl";
			LoginService loginService = (LoginService) new InitialContext()
					.lookup(url);
			IdRequest idRequest = new IdRequest(event.getKey());
			response = loginService.logout(idRequest);
		} catch (NamingException e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
	}

	@Override
	public void mapEvicted(MapEvent event) {
		LOGGER.info("mapEvicted: {}", event);
	}

	@Override
	public void mapCleared(MapEvent event) {
	}

}
