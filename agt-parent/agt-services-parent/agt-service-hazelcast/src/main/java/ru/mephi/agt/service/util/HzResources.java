package ru.mephi.agt.service.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import ru.mephi.agt.service.model.MessageList;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class HzResources {

	@Produces
	@Named(Constants.UID_MAP)
	private IMap<Long, String> uidMap;

	@Produces
	@Named(Constants.MESSAGES_MAP)
	private IMap<Long, MessageList> messagesMap;

	public HzResources() {
		// TODO: find better decision for miltiple HZ instances
		Hazelcast.shutdownAll();
		Config config = new ClasspathXmlConfig("/META-INF/hazelcast.xml");
		config.setInstanceName("agt");
		HazelcastInstance hi = Hazelcast.getOrCreateHazelcastInstance(config);
		uidMap = hi.getMap(Constants.UID_MAP);
		messagesMap = hi.getMap(Constants.MESSAGES_MAP);
	}
}
