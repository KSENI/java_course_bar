package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_mantis")
public class UserData {

    @Column(name = "username")
    private String username;

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
