package com.example.be.dto.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccountDTOLogin {
    private String username;
    private String password;
}
