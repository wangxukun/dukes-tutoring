package org.xkidea.dukestutoring.ejb;

import org.xkidea.dukestutoring.entity.Administrator;
import org.xkidea.dukestutoring.entity.Guardian;
import org.xkidea.dukestutoring.entity.Student;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Singleton
@Startup
public class ConfigBean {
    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder cb;
    @EJB
    private AdminBean adminBean;
    private static final Logger logger = Logger.getLogger("dukestutoring.ejb.ConfigBean");
    // TODO ConfigBean

    @PostConstruct
    public void init() {
        // create Maeby
        logger.info("Creating Maeby entity");
        Student maeby = new Student();
        maeby.setLastName("Fünke");
        maeby.setFirstName("Maeby");
        maeby.setGrade(10);
        maeby.setSchool("Sunshine Academy");

        // create Maeby's dad
        Guardian tobias = new Guardian();
        tobias.setFirstName("Tobias");
        tobias.setLastName("Fünke");
        tobias.setEmail("tobias@example.com");
        tobias.setPassword("javaee");

        // create Maeby's mom
        Guardian lindsey = new Guardian();
        lindsey.setFirstName("Lindsey");
        lindsey.setLastName("Fünke");
        lindsey.setEmail("lindsey@example.com");
        lindsey.setPassword("javaee");

        // create George Michael
        logger.info("Creating George Michael entity");
        Student georgeMichael = new Student();
        georgeMichael.setLastName("Bluth");
        georgeMichael.setFirstName("George");
        georgeMichael.setMiddleName("Michael");
        georgeMichael.setGrade(10);
        georgeMichael.setSchool("Huntington Beach High School");

        // create GOB
        logger.info("Creating Gob entity");
        Student gob = new Student();
        gob.setLastName("Bluth");
        gob.setFirstName("George");
        gob.setMiddleName("Oscar");
        gob.setNickname("Gob");
        gob.setGrade(12);
        gob.setSchool("Magician's Alliance Institute");

        // create Buster
        logger.info("Creating Buster entity");
        Student buster = new Student();
        buster.setFirstName("Byron");
        buster.setLastName("Bluth");
        buster.setNickname("Buster");
        buster.setGrade(11);
        buster.setSchool("Milford Academy");

        // create Lucille
        logger.info("Creating Lucille entity");
        Guardian lucille = new Guardian();
        lucille.setFirstName("Lucille");
        lucille.setLastName("Bluth");
        lucille.setEmail("lucille@example.com");
        lucille.setPassword("javaee");

        logger.info("Calling createStudent() on Maeby");
        String result = adminBean.createStudent(maeby);
        logger.info("Calling createStudent() on George Michael");
        result = adminBean.createStudent(georgeMichael);
        logger.info("Calling createStudent() on Gob");
        result = adminBean.createStudent(gob);

        logger.info("Calling createGuardian() for Maeby's parents");
        adminBean.createGuardian(tobias, maeby);
        adminBean.createGuardian(lindsey, maeby);
    }
}
