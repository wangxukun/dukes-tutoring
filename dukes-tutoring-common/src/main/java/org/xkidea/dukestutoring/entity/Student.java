package org.xkidea.dukestutoring.entity;

import org.xkidea.dukestutoring.util.StatusType;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
// TODO NamedQuery
@Entity
@XmlRootElement(name = "Student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student extends Person implements Serializable {
    private static final long serialVersionUID = 1604647822781150243L;
    @ManyToMany(mappedBy = "students")
    protected List<TutoringSession> sessions;
    protected String school;
    @Digits(integer = 2, fraction = 0, message = "{invalid.grade.level}")
    protected int grade;
    @ManyToMany
    @XmlTransient
    private List<Guardian> guardians;
    @Enumerated(EnumType.STRING)
    protected StatusType status = StatusType.OUT;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @XmlTransient
    protected List<StatusEntry> statusEntries;
    protected boolean active;

    public Student() {
        this.active = true;
        this.sessions = new ArrayList<>();
        this.statusEntries = new ArrayList<>();
        this.guardians = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public List<TutoringSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<TutoringSession> sessions) {
        this.sessions = sessions;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(List<Guardian> guardians) {
        this.guardians = guardians;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public List<StatusEntry> getStatusEntries() {
        return statusEntries;
    }

    public void setStatusEntries(List<StatusEntry> statusEntries) {
        this.statusEntries = statusEntries;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStatusLabel() {
        return this.status.toString();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}
