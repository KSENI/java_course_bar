package ru.stqa.addressbook.model;

import java.util.Objects;

public class PersonData {
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String email1;
    private String email2;
    private String email3;
    private String homepage;
    private int id;

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
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
