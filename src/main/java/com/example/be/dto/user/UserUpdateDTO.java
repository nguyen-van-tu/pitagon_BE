package com.example.be.dto.user;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserUpdateDTO {

    private String idString;

    @Pattern(regexp = "[\\p{L}\\s]{1,30}", message = "First name must be between 1 and 30 characters long and contain no special characters")
    @NotBlank(message = "First name is required;")
    private String firstName;

    @Pattern(regexp = "[\\p{L}\\s]{0,30}", message = "Mid name is less than 30 characters and no special characters;")
    private String midName;

    @Pattern(regexp = "[\\p{L}\\s]{1,30}", message = "Last name must be between 1 and 30 characters long and contain no special characters")
    @NotBlank(message = "Last name is required;")
    private String lastName;

    @Pattern(regexp = "[\\p{L}\\s]{1,30}", message = "Display name must be between 1 and 30 characters long and contain no special characters")
    @NotBlank(message = "Display name is required;")
    private String displayName;

    @Pattern(regexp = "[\\p{L}\\s\\d.\\-,@/\\\\]{4,200}", message = "Address must be between 4 and 200 characters long and contain no special characters")
    @NotBlank(message = "Address is required;")
    private String address;

    @NotBlank(message = "Birthday is required;")
    private String dateOfBirth;

    @Min(value = 1, message = "The gender must contain 1 or 2;")
    @Max(value = 2, message = "The gender must contain 1 or 2;")
    private int gender;

    @Pattern(regexp = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))", message = "Email is invalid")
    @NotBlank(message = "Email is required;")
    private String email;

    @Pattern(regexp = "([+|0])(\\d){8,30}", message = "Phone is starting with 0 or +, minimum 9 and maximum 30 characters;")
    @NotBlank(message = "Phone is required;")
    private String phone;

}
