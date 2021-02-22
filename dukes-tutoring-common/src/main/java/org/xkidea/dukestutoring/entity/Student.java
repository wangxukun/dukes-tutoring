package org.xkidea.dukestutoring.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Entity
@XmlRootElement(name = "Student")
// TODO @XmlAccessorType()
public class Student extends Person implements Serializable {
    private static final long serialVersionUID = 1604647822781150243L;
    // TODO TutoringSession
    protected List<TutoringSession> sessions;
    protected String school;
    protected int grade;
    @ManyToMany
    private List<Guardian> guardians;
}
