package model;

import java.util.Objects;

public class User {

    private String username;
    private String usermail;
    private String password;


    public User(String username, String password,String usermail) {
        this.username = username;
        this.password = password;
        this.usermail = usermail;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserMail() {
        return usermail;
    }
    public void setUserMail(String password) {
        this.usermail = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", usermail='" + usermail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword())&&
                Objects.equals(getUserMail(), user.getUserMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(),getUserMail());
    }
}
