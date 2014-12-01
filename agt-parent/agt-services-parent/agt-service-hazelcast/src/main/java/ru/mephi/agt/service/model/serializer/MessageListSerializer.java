package ru.mephi.agt.service.model.serializer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.service.model.MessageList;
import ru.mephi.agt.service.util.MySerializationConstants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

public class MessageListSerializer implements StreamSerializer<MessageList> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageListSerializer.class);

	private ObjectMapper om;

	public MessageListSerializer() {
		om = new ObjectMapper();
		LOGGER.info("Instanse constructred");
	}

	@Override
	public int getTypeId() {
		return MySerializationConstants.MESSAGE_LIST_TYPE;
	}

	@Override
	public void destroy() {
		LOGGER.info("Destroyed");

	}

	@Override
	public void write(ObjectDataOutput out, MessageList object)
			throws IOException {
		out.writeUTF(om.writeValueAsString(object));
	}

	@Override
	public MessageList read(ObjectDataInput in) throws IOException {
		String json = in.readUTF();
		return om.readValue(json, MessageList.class);
	}

}
