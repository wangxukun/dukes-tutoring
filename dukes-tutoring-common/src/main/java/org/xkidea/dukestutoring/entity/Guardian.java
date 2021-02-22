package org.xkidea.dukestutoring.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 监护人类
 */
@Entity
@XmlRootElement(name = "Guardian")
public class Guardian extends Person implements Serializable {
    private static final long serialVersionUID = -8539117082826840580L;
    @ManyToMany(mappedBy = "guardians")
    private List<Student> students;
    protected boolean active;

    public Guardian() {
        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardian guardian = (Guardian) o;
        return active == guardian.active && Objects.equals(students, guardian.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, active);
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "id=" + id +
                '}';
    }
}
