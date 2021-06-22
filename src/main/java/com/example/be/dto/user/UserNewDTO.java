package com.example.be.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserNewDTO {

    @Pattern(regexp = "[\\w\\d]{5,20}", message = "Username must be between 5 and 20 characters long and contain no special characters")
    @NotBlank(message = "Username is required;")
    private String username;

    private String password;

    @Pattern(regexp = "[\\p{L}\\s]{5,50}", message = "Full name must be between 5 and 50 characters long and contain no special characters")
    @NotBlank(message = "Full name is required;")
    private String fullName;

    @Pattern(regexp = "[\\p{L}\\s\\d.\\-,@/\\\\]{4,200}", message = "Address must be between 4 and 200 characters long and contain no special characters")
    @NotBlank(message = "Address is required;")
    private String currentAddress;
}
