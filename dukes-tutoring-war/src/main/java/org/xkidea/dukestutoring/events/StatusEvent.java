package org.xkidea.dukestutoring.events;

import org.xkidea.dukestutoring.entity.Student;

import java.io.Serializable;

public class StatusEvent implements Serializable {
    private static final long serialVersionUID = -2279872549728187286L;

    private Student student;

    public StatusEvent() {
    }

    public StatusEvent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
