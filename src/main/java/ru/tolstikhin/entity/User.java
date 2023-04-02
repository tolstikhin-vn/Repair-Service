package ru.tolstikhin.entity;

public class User {
    private String login;
    private String password;
    private String surname;
    private String name;

    public User(String login, String password, String surname, String name) {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
    }

    public User() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFam() {
        return surname;
    }

    public void setFam(String fam) {
        this.surname = fam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
