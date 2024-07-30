package com.boardingmoneymanegement.demo.services.auth;


import com.boardingmoneymanegement.demo.dto.SignUpRequest;
import com.boardingmoneymanegement.demo.dto.UserDto;
import com.boardingmoneymanegement.demo.entity.User;
import com.boardingmoneymanegement.demo.enums.UserRole;
import com.boardingmoneymanegement.demo.repository.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepostory userRepository;

    @Override
    public UserDto createdCustomer(SignUpRequest signuprequest) {
        User user = new User();
        user.setName(signuprequest.getName());
        user.setEmail(signuprequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signuprequest.getPassword()));
        user.setUserRole(UserRole.STUDENT);
        User createduser  =userRepository.save(user);
        UserDto userdto = new UserDto();
        userdto.setId(createduser.getId());
        return userdto;
    }

    @Override
    public boolean hascustomerwithemail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
