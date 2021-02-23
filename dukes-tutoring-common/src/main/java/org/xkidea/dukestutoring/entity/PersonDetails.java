package org.xkidea.dukestutoring.entity;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class PersonDetails implements Serializable {
    private static final long serialVersionUID = -1270098016711047322L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "details", cascade = CascadeType.ALL)
    protected Person person;
    @Lob
    @XmlTransient // 防止将JavaBean属性/类型映射到XML表示形式。
    protected byte[] photo;
    @Past // 带注释的元素必须是过去的日期或时间
    @Temporal(TemporalType.DATE)
    protected Date birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDetails that = (PersonDetails) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "id=" + id +
                '}';
    }
}
