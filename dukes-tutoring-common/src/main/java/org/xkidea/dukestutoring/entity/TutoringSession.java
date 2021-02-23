package org.xkidea.dukestutoring.entity;

import org.xkidea.dukestutoring.util.CalendarUtil;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * TutoringSession实体表示辅导中心的某一天。这个辅导班实体会记录这一天哪些
 * 学生在位，哪些学生去了公园。
 */
@Entity
// TODO NamedQuery
@XmlRootElement(name = "TutoringSession")
@XmlAccessorType(XmlAccessType.FIELD) // 除非由XmlTransient注释，否则JAXB绑定类中的每个非静态，非瞬态字段都将自动绑定到XML。 Getter / setter对仅在被某些JAXB注释显式注释时才绑定到XML。
public class TutoringSession implements Serializable {
    private static final long serialVersionUID = 863514298963652356L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @XmlTransient
    @ManyToMany
    private final List<Student> students;
    @OneToMany(mappedBy = "tutoringSession",cascade = CascadeType.ALL)
    private List<StatusEntry> statusEntries;
    @Temporal(value = TemporalType.DATE)
    private Calendar sessionDate;

    public TutoringSession() {
        Calendar cal = Calendar.getInstance();
        CalendarUtil.stripTime(cal);
        sessionDate = cal;
        students = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<StatusEntry> getStatusEntries() {
        return statusEntries;
    }

    public void setStatusEntries(List<StatusEntry> statusEntries) {
        this.statusEntries = statusEntries;
    }

    public Calendar getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Calendar sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TutoringSession)) {
            return false;
        }
        TutoringSession other = (TutoringSession) object;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))) {
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
        return "TutoringSession{" +
                "id=" + id +
                '}';
    }
}
