package com.boardingmoneymanegement.demo.dto;

import com.boardingmoneymanegement.demo.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {



    private Long id;
    private String name;
    private String email;

    private UserRole userRole;

}
