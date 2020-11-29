package com.victorp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    private String first_name;
    private String last_name;
    private String email;
    private List<String> roles = new ArrayList<>();
    private List<String> phone_numbers = new ArrayList<>();

    public User() {
    }

    public User(String first_name, String last_name, String email, List<String> roles, List<String> phone_numbers) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.roles = roles;
        this.phone_numbers = phone_numbers;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public void setRoles(String role){
        this.roles.add(role);
    }

    public List<String> getPhone_numbers() {
        return phone_numbers;
    }

    public void setPhone_numbers(List<String> phone_numbers) {
        this.phone_numbers = phone_numbers;
    }
    public void setPhone_numbers(String phone_number){
        this.phone_numbers.add(phone_number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(first_name, user.first_name) &&
                Objects.equals(last_name, user.last_name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(phone_numbers, user.phone_numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, email, roles, phone_numbers);
    }

    @Override
    public String toString() {
        return "User: " +
                 first_name + " " +
                 last_name + "\n" +
                 "email: " + email + "\n"  +
                 "roles: " + roles + "\n" +
                 "phone numbers: " + phone_numbers + "\n";
    }

}
