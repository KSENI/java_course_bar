package ru.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class PersonData {
    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "middlename")
    @Expose
    private String middleName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Type(type = "text")
    @Column(name = "home")
    private String homePhone;
    @Expose
    @Type(type = "text")
    @Column(name = "mobile")
    private String mobilePhone;
    @Expose
    @Type(type = "text")
    @Column(name = "work")
    private String workPhone;
    @Expose
    @Type(type = "text")
    @Column(name = "email")
    private String email1;
    @Expose
    @Type(type = "text")
    @Column(name = "email2")
    private String email2;
    @Expose
    @Type(type = "text")
    @Column(name = "email3")
    private String email3;
    @Type(type = "text")
    @Expose
    @Column(name = "homepage")
    private String homepage;
    @Id
    @Column(name = "id")
    private int id;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;

    public PersonData() {
    }

    public PersonData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonData withAddress(String address) {
        this.address = address;
        return this;
    }

    public PersonData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public PersonData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public PersonData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public PersonData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public PersonData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public PersonData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public PersonData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public PersonData withId(int id) {
        this.id = id;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public PersonData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public PersonData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    @Override
    public String toString() {
        return "PersonData{" +
                "id='" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", homepage='" + homepage + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonData that = (PersonData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(email1, that.email1) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(homepage, that.homepage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, address, homePhone, mobilePhone, workPhone, email1, email2, email3, homepage, id);
    }
}
