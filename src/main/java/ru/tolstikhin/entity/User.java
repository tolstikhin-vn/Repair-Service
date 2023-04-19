package ru.tolstikhin.entity;

public class User {

    private int id;
    private String login;
    private String password;
    private String surname;
    private String name;
    private boolean active;
    private boolean deleted;
    private int passwordFailCount;
    private int defaultPasswordFailCount;
    private String lastLogin;
    private String lastLogout;

    public User(String login, String password, String surname, String name) {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
    }

    public User(int id, String login, String password, String surname, String name, boolean active, boolean deleted, int passwordFailCount, int defaultPasswordFailCount, String lastLogin, String lastLogout) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.active = active;
        this.deleted = deleted;
        this.passwordFailCount = passwordFailCount;
        this.defaultPasswordFailCount = defaultPasswordFailCount;
        this.lastLogin = lastLogin;
        this.lastLogout = lastLogout;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getPasswordFailCount() {
        return passwordFailCount;
    }

    public void setPasswordFailCount(int passwordFailCount) {
        this.passwordFailCount = passwordFailCount;
    }

    public int getDefaultPasswordFailCount() {
        return defaultPasswordFailCount;
    }

    public void setDefaultPasswordFailCount(int defaultPasswordFailCount) {
        this.defaultPasswordFailCount = defaultPasswordFailCount;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(String lastLogout) {
        this.lastLogout = lastLogout;
    }
}
