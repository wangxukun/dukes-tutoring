package org.xkidea.dukestutoring.ejb;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

@Singleton
@Startup
public class ConfigBean {
    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder cb;
}
