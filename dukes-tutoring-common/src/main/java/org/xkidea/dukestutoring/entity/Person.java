package org.xkidea.dukestutoring.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Entity
@XmlRootElement(name = "Person")
public class Person implements Serializable {
    private static final long serialVersionUID = 6593948343502557831L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String middleName;
    protected String nickname;
    protected String suffix;
    protected String email;
    protected String homePhone;
    protected String mobilePhone;
    @OneToMany // 一个实体(Person)的一个实例与另一个实体(Address)的多个实例关联
    protected List<Address> address;
    @OneToOne
    protected PersonDetails details;
    protected String password;
    // TODO ...
}
