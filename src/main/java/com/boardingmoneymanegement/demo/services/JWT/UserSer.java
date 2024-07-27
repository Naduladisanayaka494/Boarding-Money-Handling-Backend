package com.boardingmoneymanegement.demo.services.JWT;

import com.boardingmoneymanegement.demo.repository.UserRepostory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserSer implements UserService{
    private final UserRepostory userRepository;



    public UserSer(UserRepostory userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsService userDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
            }
        };
    }
}
