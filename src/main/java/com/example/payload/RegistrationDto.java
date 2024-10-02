package com.example.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class RegistrationDto {
    @Size(min = 2,message = "Minimum of 2 characters are required")
    private String firstName;
    @Email
    private String email;
    @Size(min=10,max = 10,message = "Phone number must be exactly 10 digits")
    private String mobile;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
