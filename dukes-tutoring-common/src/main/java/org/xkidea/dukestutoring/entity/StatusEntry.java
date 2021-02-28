package org.xkidea.dukestutoring.entity;

import org.xkidea.dukestutoring.util.StatusType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * 状态条目实体，学生的状态改变时，StatusEntry实体将状态记入日志，这个实体与
 * TutoringSession实体关联。
 */
@Entity
@XmlRootElement(name = "StatusEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusEntry implements Serializable {
    private static final long serialVersionUID = 1477788524193051194L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusType currentStatus;
    @ManyToOne
    @XmlTransient
    private Student student;
    @ManyToOne
    @XmlTransient
    private TutoringSession tutoringSession;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar statusDate;

    public StatusEntry() {
    }

    public StatusEntry(StatusType status, Student student, TutoringSession session) {
        this.setCurrentStatus(status);
        this.setStudent(student);
        this.setTutoringSession(session);
        this.setStatusDate(Calendar.getInstance());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusType getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(StatusType currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TutoringSession getTutoringSession() {
        return tutoringSession;
    }

    public void setTutoringSession(TutoringSession tutoringSession) {
        this.tutoringSession = tutoringSession;
    }

    public Calendar getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Calendar statusDate) {
        this.statusDate = statusDate;
    }

    public String getFormattedStatusDate() {
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
        return df.format(this.statusDate.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StatusEntry)) {
            return false;
        }
        StatusEntry other = (StatusEntry) o;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "StatusEntry{" +
                "id=" + id +
                '}';
    }
}
