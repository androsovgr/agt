package ru.mephi.agt.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.MapEvent;

public class UidListener implements EntryListener<Long, String> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UidListener.class);

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
		LOGGER.info("entryEvicted: {}", event);
	}

	@Override
	public void mapEvicted(MapEvent event) {
		LOGGER.info("mapEvicted: {}", event);
	}

	@Override
	public void mapCleared(MapEvent event) {
	}

}
