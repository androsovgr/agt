package ru.mephi.agt.service.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {
	// use @SuppressWarnings to tell IDE to ignore warnings about field not
	// being referenced directly
	@Produces
	@PersistenceContext
	private EntityManager em;

}