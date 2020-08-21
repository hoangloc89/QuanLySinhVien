/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

/**
 *
 * @author DIENMAYXANH
 */
public class User {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole role;

    public User() {
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.role = null;
    }

    public User(String email, String firstName, String lastName, String password, UserRole role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public boolean checkLogin(String email, String password) {
        return (this.email.equalsIgnoreCase(email) && this.password.equals(password));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}
