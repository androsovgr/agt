package ru.mephi.agt.desktop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.api.ApiService;

public class ServerConnector {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerConnector.class);

	private static ApiService apiInterface;
	private static ResteasyClient client;

	private static final String API_CONTEXT_PROPERTY = "api-context";

	public static ApiService getApiInterface() {
		if (apiInterface == null) {
			synchronized ("") {
				if (apiInterface == null) {
					initApuInterface();
				}
			}
		}
		return apiInterface;
	}

	private static void initApuInterface() {
		client = getClient();
		String context = getContext();
		if (context != null) {
			ResteasyWebTarget target = client.target(context);
			apiInterface = target.proxy(ApiService.class);
			LOGGER.info("Created REST client for context: {}", context);
		}
	}

	private static ResteasyClient getClient() {
		if (client == null) {
			synchronized ("") {
				if (client == null) {
					client = new ResteasyClientBuilder().build();
				}
			}
		}
		return client;
	}

	private static String getContext() {
		String context = null;
		File file = new File(ServerConnector.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath());
		String path = file.getParent() + "/resources/property.properties";
		try (InputStream input = new FileInputStream(path)) {
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			context = prop.getProperty(API_CONTEXT_PROPERTY);
		} catch (IOException e) {
			LOGGER.error("Can't get property file by path={}", path, e);
		}
		return context;
	}
}
