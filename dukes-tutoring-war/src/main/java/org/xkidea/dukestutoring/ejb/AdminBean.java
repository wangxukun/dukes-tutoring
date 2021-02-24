package org.xkidea.dukestutoring.ejb;

import org.xkidea.dukestutoring.entity.*;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//@Path("/tutoring/admin")
@Stateless
@Named
public class AdminBean {
    @PersistenceContext
    private EntityManager em;
    private static final Logger logger =
            Logger.getLogger("dukestutoring.ejb.AdminBean");
    private CriteriaBuilder cb;
    private String username;

    @PostConstruct
    private void init(){
        cb = em.getCriteriaBuilder();
    }

    public Student createStudent(String firstName, String middleName, String lastName,
                                 String nickname, String suffix, String school,
                                 int grade, String email, String homePhone, String mobilePhone) {
        logger.log(Level.INFO,"AdminBean.createStudent(10 args): Persisting new student.");

        Student student = new Student();

        student.setFirstName(firstName);
        student.setMiddleName(middleName);
        student.setLastName(lastName);
        student.setNickname(nickname);
        student.setSuffix(suffix);
        student.setSchool(school);
        student.setGrade(grade);
        student.setEmail(email);
        student.setHomePhone(homePhone);
        student.setMobilePhone(mobilePhone);

        em.persist(student);

        return student;
    }

    public String createStudent(Student student) {
        logger.log(Level.INFO,"AdminBean.createStudent(1 args): Persisting new student.");

        em.persist(student);
        return "createStudent";
    }

    public void createStudents(List<Student> students) {
        for (Student s : students) {
            this.createStudent(s);
        }
    }

    // TODO editStudent removeStudent

    public String createGuardian(Guardian guardian, Student student) {
        logger.log(Level.INFO,"Creating guardian {0} for {1}",
                new Object[]{guardian.getName(),student.getName()});
        student.getGuardians().add(guardian);
        guardian.getStudents().add(student);
        /**
         * 查找具有相同ID的附加对象并进行更新。
         * 如果存在，请更新并返回已连接的对象。
         * 如果不存在，则将新的寄存器插入数据库。
         */
        em.merge(student);
        /**
         * 将新的寄存器插入数据库。
         * 将对象附加到实体管理器。
         * 它确保您正在插入而不是错误地更新。
         */
        em.persist(guardian);
        return "createGuardian";
    }

    public Guardian createGuardian(String firstName, String middleName,
                                   String lastName, String nickname, String suffix, String email,
                                   String homePhone, String mobilePhone, Student student) {
        logger.log(Level.INFO,
                "AdminBean.createGuardian(9 args): Persisting new guardian.");

        Guardian guardian = new Guardian();

        guardian.setFirstName(firstName);
        guardian.setMiddleName(middleName);
        guardian.setLastName(lastName);
        guardian.setNickname(nickname);
        guardian.setSuffix(suffix);
        guardian.setEmail(email);
        guardian.setHomePhone(homePhone);
        guardian.setMobilePhone(mobilePhone);
        student.getGuardians().add(guardian);
        guardian.getStudents().add(student);
        em.merge(student);
        em.persist(guardian);

        return guardian;
    }

    public String createGuardianWithList(Guardian guardian, List<Student> students) {
        for (Student s : students) {
            s.getGuardians().add(guardian);
            guardian.getStudents().add(s);
            em.merge(s);
        }
        em.persist(guardian);
        return "createdGuardian";
    }

    // TODO editGuardian removeGuardianFromStudent

    public String addGuardiansToStudent(List<Guardian> guardians, Student student) {
        for (Guardian g : guardians) {
            student.getGuardians().add(g);
            g.getStudents().add(student);
            em.merge(g);
        }
        em.merge(student);
        return "addedGuardians";
    }

    public String createAddress(Address address, Person person) {
        person.getAddresses().add(address);
        address.setPerson(person);
        em.merge(person);
        em.persist(address);
        return "createAddress";
    }

    public Address createAddress(String street1, String street2, String city,
                                 String province, String country, String postalCode,
                                 Boolean isPrimary, Student student) {
        Address address = new Address();
        address.setStreet1(street1);
        address.setStreet2(street2);
        address.setCity(city);
        address.setProvince(province);
        address.setCountry(country);
        address.setPostalCode(postalCode);
        address.setPrimary(isPrimary);
        address.setPerson(student);
        student.getAddresses().add(address);
        address.setPerson(student);
        em.merge(student);
        em.persist(address);

        return address;
    }

    // TODO editAddress removeAddress

    // TODO getAllGuardians getAllAddresses getAllInactiveStudents

    public String activateStudent(Student student) {
        student.setActive(true);
        em.merge(student);
        return "activatedStudent";
    }

    // TODO findStudentById findGuardianById

    public String createAdministrator(Administrator admin) {
        em.persist(admin);
        return "createdAdministrator";
    }

    public String getUsername() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Administrator");
    }

    public String logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession(); // 使session失效
        return "../index.xhtml?faces-redirect=true";
    }
}
