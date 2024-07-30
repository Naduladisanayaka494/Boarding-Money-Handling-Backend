package com.boardingmoneymanegement.demo.services.auth;

import com.boardingmoneymanegement.demo.dto.SignUpRequest;
import com.boardingmoneymanegement.demo.dto.UserDto;

public interface AuthService {
    UserDto createdCustomer(SignUpRequest signuprequest);
    boolean hascustomerwithemail(String email);
}
