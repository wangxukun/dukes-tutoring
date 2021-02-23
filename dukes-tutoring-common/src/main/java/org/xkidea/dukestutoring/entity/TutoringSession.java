package org.xkidea.dukestutoring.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * TutoringSession实体表示辅导中心的某一天。这个辅导班实体会记录这一天哪些
 * 学生在位，哪些学生去了公园。
 */
@Entity
@XmlRootElement(name = "TutoringSession")
@XmlAccessorType(XmlAccessType.FIELD) // 除非由XmlTransient注释，否则JAXB绑定类中的每个非静态，非瞬态字段都将自动绑定到XML。 Getter / setter对仅在被某些JAXB注释显式注释时才绑定到XML。
public class TutoringSession implements Serializable {
    private static final long serialVersionUID = 863514298963652356L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final List<Student> students;
    private List<StatusEntry> statusEntries;

    private Calendar sessionDate;
}
