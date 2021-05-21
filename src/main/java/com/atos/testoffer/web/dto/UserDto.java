package com.atos.testoffer.web.dto;

import com.atos.testoffer.validation.ValidAge;

import javax.validation.constraints.NotNull;

public class UserDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String password;

    @NotNull
    private String email;

    private Boolean enabled;

    @ValidAge
    private Integer age;


    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=")
                .append(firstName)
                .append(", lastName=")
                .append(lastName)
                .append(", email=")
                .append(email)
                .append(", age=")
                .append(age);
        return builder.toString();
    }

}
