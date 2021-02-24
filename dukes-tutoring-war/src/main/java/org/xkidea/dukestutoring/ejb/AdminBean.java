package org.xkidea.dukestutoring.ejb;

import org.xkidea.dukestutoring.entity.Guardian;
import org.xkidea.dukestutoring.entity.Student;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/tutoring/admin")
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
}
