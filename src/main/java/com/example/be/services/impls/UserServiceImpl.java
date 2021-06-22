package com.example.be.services.impls;

import com.example.be.commons.FormatString;
import com.example.be.dto.authentication.AccountBasicInfoDTO;
import com.example.be.dto.user.UserDeleteDTO;
import com.example.be.dto.user.UserListDTO;
import com.example.be.dto.user.UserNewDTO;
import com.example.be.dto.user.UserUpdateDTO;
import com.example.be.models.User;
import com.example.be.repositories.UserRepository;
import com.example.be.services.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    User user = new User();

    @Override
    public UserDetails loadUserByUsername(String username) {
        user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with Email: " + username);
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        Set<Role> roles = account.getRoles();
//        for(Role role: roles){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
        }
    }

    @Override
    public AccountBasicInfoDTO getAccountBasicInfo() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, AccountBasicInfoDTO.class);
    }

    @Override
    public Page<UserListDTO> findAll(Pageable pageable, String search, int isDeleted) {
        ModelMapper modelMapper = new ModelMapper();
        return userRepository.findAll(pageable, search, isDeleted).map(user -> modelMapper.map(user, UserListDTO.class));
    }

    @Override
    public Boolean checkUsernameExists(String username) {
        return userRepository.findByUserName(username) != null;
    }

    @Override
    public Boolean checkEmailExists(String email, String userName) {
        return userRepository.findByEmailAndUserNameIsNot(email,userName) != null;
    }

    @Override
    public void addNewUser(UserNewDTO userNewDTO) {
        user = new User();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(userNewDTO, user);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        user = userRepository.findById(new ObjectId(userUpdateDTO.getIdString())).orElse(null);
        if (user != null) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(userUpdateDTO, user);
            user.setFirstName(FormatString.formatName(user.getFirstName()));
            user.setMidName(FormatString.formatName(user.getMidName()));
            user.setLastName(FormatString.formatName(user.getLastName()));
            user.setFirstNameAccented(FormatString.removeAccent(user.getFirstName()));
            user.setMidNameAccented(FormatString.removeAccent(user.getMidName()));
            user.setLastNameAccented(FormatString.removeAccent(user.getLastName()));
            user.setAddressAccented(FormatString.removeAccent(user.getAddress()));
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void deleteUser(UserDeleteDTO[] listUserDeleteDTO) {
        if (listUserDeleteDTO != null && listUserDeleteDTO.length > 0) {
            if (listUserDeleteDTO[0].getIsDeleted() == 1) {
                for (UserDeleteDTO userDeleteDTO : listUserDeleteDTO) {
                    userRepository.deleteById(new ObjectId(userDeleteDTO.getIdString()));
                }
            } else {
                for (UserDeleteDTO userDeleteDTO : listUserDeleteDTO) {
                    user = userRepository.findById(new ObjectId(userDeleteDTO.getIdString())).orElse(null);
                    if (user != null) {
                        user.setIsDeleted(1);
                        userRepository.save(user);
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public void reactiveUser(UserDeleteDTO[] listUserDeleteDTO) {
        if (listUserDeleteDTO != null && listUserDeleteDTO.length > 0) {
            for (UserDeleteDTO userDeleteDTO : listUserDeleteDTO) {
                user = userRepository.findById(new ObjectId(userDeleteDTO.getIdString())).orElse(null);
                if (user != null) {
                    user.setIsDeleted(0);
                    userRepository.save(user);
                }
            }
        }
    }
}
