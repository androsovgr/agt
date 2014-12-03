package ru.mephi.agt.service.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import ru.mephi.agt.service.model.MessageList;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ISet;

public class HzResources {

	@Produces
	@Named(Constants.UID_MAP)
	private IMap<Long, String> uidMap;

	@Produces
	@Named(Constants.MESSAGES_MAP)
	private IMap<Long, MessageList> messagesMap;

	@Produces
	@Named(Constants.USER_SET)
	private ISet<Long> userSet;

	public HzResources() {
		// TODO: find better decision for miltiple HZ instances
		HazelcastInstance hi = Hazelcast.getHazelcastInstanceByName("agt");
		if (hi == null) {
			synchronized ("const") {
				if (hi == null) {
					Config config = new ClasspathXmlConfig(
							"/META-INF/hazelcast.xml");
					config.setInstanceName("agt");
					hi = Hazelcast.getOrCreateHazelcastInstance(config);
				}
			}
		}
		uidMap = hi.getMap(Constants.UID_MAP);
		messagesMap = hi.getMap(Constants.MESSAGES_MAP);
		userSet = hi.getSet(Constants.USER_SET);
	}
}
