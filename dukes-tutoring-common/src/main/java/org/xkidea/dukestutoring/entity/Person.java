package org.xkidea.dukestutoring.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
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
    protected List<Address> addresses;
    @OneToOne
    protected PersonDetails details;
    @XmlTransient
    protected String password;

    public Person() {
        this.addresses = new ArrayList<>();
    }

    public Person(String firstName, String lastName, String middleName, String suffix, String email, String homePhone, String mobilePhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.email = email;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
    }

    public Person(String firstName, String lastName, String email, String homePhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.homePhone = homePhone;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> address) {
        this.addresses = address;
    }

    public PersonDetails getDetails() {
        return details;
    }

    public void setDetails(PersonDetails details) {
        this.details = details;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        if (null == this.nickname || nickname.length() < 1) {
            return this.getFirstName() + " " + this.getLastName();
        } else {
            return this.getNickname() + " " + this.getLastName();
        }
    }
}
