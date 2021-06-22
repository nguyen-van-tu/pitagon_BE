
package com.example.be.controllers;

import com.example.be.commons.ConstURL;
import com.example.be.commons.SortData;
import com.example.be.dto.authentication.AccountDTOLogin;
import com.example.be.dto.authentication.JwtResponse;
import com.example.be.dto.user.UserDeleteDTO;
import com.example.be.dto.user.UserListDTO;
import com.example.be.dto.user.UserNewDTO;
import com.example.be.dto.user.UserUpdateDTO;
import com.example.be.securities.JwtTokenUtil;
import com.example.be.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ConstURL.URL_USER)
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = ConstURL.URL_LOGIN)
    public ResponseEntity<JwtResponse> login(@RequestBody AccountDTOLogin accountDTOLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountDTOLogin.getUsername(), accountDTOLogin.getPassword())
        );
        UserDetails userDetails =
                userService.loadUserByUsername(authentication.getName().toLowerCase());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userService.getAccountBasicInfo()));
    }

    @GetMapping()
    public Page<UserListDTO> getAllUsers(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10")
            @Min(value = 1, message = "size must be greater than or equal to 1")
            @Max(value = 100, message = "size must be lower than or equal to 100") int size,
            @RequestParam(defaultValue = "userName,asc") String[] sort,
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "isDeleted", required = false, defaultValue = "0") int isDeleted
    ) {
       Page<UserListDTO> a = userService.findAll(PageRequest.of(page, size, Sort.by(SortData.getOrderByParamSort(sort))), search, isDeleted);
        return userService.findAll(PageRequest.of(page, size, Sort.by(SortData.getOrderByParamSort(sort))), search, isDeleted);
    }

    @GetMapping(value = ConstURL.URL_CHECK_USERNAME_EXISTS)
    public ResponseEntity<?> checkUsername(@RequestParam
                                           @Pattern(regexp = "[\\w\\d]{5,20}", message = "Username must be between 5 and 20 characters long and contain no special characters")
                                                       String paramCheck) {
        return ResponseEntity.status(200).body(userService.checkUsernameExists(paramCheck));
    }

    @GetMapping(value = ConstURL.URL_CHECK_EMAIL_EXISTS)
    public ResponseEntity<?> checkEmail(@RequestParam
                                           @Pattern(regexp = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))",
                                                   message = "Email is invalid")
                                                   String paramCheck,
                                           Authentication authentication) {
        return ResponseEntity.status(200).body(userService.checkEmailExists(paramCheck, authentication.getName()));
    }

    @PostMapping(value = ConstURL.URL_ADD)
    public ResponseEntity<?> addUser(@Valid @RequestBody UserNewDTO userNewDTO) {
        if (!userService.checkUsernameExists(userNewDTO.getUsername())) {
            userNewDTO.setPassword(passwordEncoder.encode(userNewDTO.getPassword()));
            userService.addNewUser(userNewDTO);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(500).body("Username already exists");
        }
    }

    @PostMapping(value = ConstURL.URL_SAVE)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {

            userService.updateUser(userUpdateDTO);
            return ResponseEntity.ok(null);
    }

    @PostMapping(value = ConstURL.URL_DELETE)
    public ResponseEntity<?> deleteUser(@RequestBody UserDeleteDTO[] userDeleteDTO) {
        userService.deleteUser(userDeleteDTO);
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = ConstURL.URL_REACTIVE)
    public ResponseEntity<?> reactiveUser(@RequestBody UserDeleteDTO[] userDeleteDTO) {
        userService.reactiveUser(userDeleteDTO);
        return ResponseEntity.ok(null);
    }
}

