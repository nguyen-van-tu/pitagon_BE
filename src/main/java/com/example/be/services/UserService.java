package com.example.be.services;

import com.example.be.dto.authentication.AccountBasicInfoDTO;
import com.example.be.dto.user.UserDeleteDTO;
import com.example.be.dto.user.UserListDTO;
import com.example.be.dto.user.UserNewDTO;
import com.example.be.dto.user.UserUpdateDTO;
import com.example.be.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    AccountBasicInfoDTO getAccountBasicInfo();
    Page<UserListDTO> findAll(Pageable pageable, String search, int isDeleted);
    Boolean checkUsernameExists(String username);
    Boolean checkEmailExists(String email, String userName);
    void addNewUser(UserNewDTO userNewDTO);
    void updateUser(UserUpdateDTO userUpdateDTO);
    void deleteUser(UserDeleteDTO[] listUserDeleteDTO);
    void reactiveUser(UserDeleteDTO[] listUserDeleteDTO);
}
