package com.boardingmoneymanegement.demo.services.auth;

import com.boardingmoneymanegement.demo.dto.SignUpRequest;
import com.boardingmoneymanegement.demo.dto.UserDto;
import com.boardingmoneymanegement.demo.entity.User;
import com.boardingmoneymanegement.demo.enums.UserRole;

import java.util.List;

public interface AuthService {
    UserDto createdCustomer(SignUpRequest signuprequest);
    boolean hascustomerwithemail(String email);

    List<User> getAllUsers();

    List<UserDto> getAllStudents();




}
