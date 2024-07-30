//package com.boardingmoneymanegement.demo.controller;
//
//
//import com.boardingmoneymanegement.demo.dto.AuthenticationRequest;
//import com.boardingmoneymanegement.demo.dto.AuthenticationResponse;
//import com.boardingmoneymanegement.demo.dto.SignUpRequest;
//import com.boardingmoneymanegement.demo.dto.UserDto;
//import com.boardingmoneymanegement.demo.entity.User;
//import com.boardingmoneymanegement.demo.repository.UserRepostory;
//import com.boardingmoneymanegement.demo.services.JWT.UserService;
//import com.boardingmoneymanegement.demo.services.auth.AuthServiceImpl;
//import com.boardingmoneymanegement.demo.utill.JWTUtill;
//import lombok.RequiredArgsConstructor;
//import org.apache.coyote.BadRequestException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final AuthServiceImpl authService;
//    private final AuthenticationManager authenticationManager;
//    private final UserService userService;
//    private final JWTUtill jwtUtill;
//    private  final UserRepostory userRepository;
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> signupCustomer(@RequestBody SignUpRequest signupRequest) {
//        if(authService.hascustomerwithemail(signupRequest.getEmail()))
//            return new ResponseEntity<>("email already exists",HttpStatus.NOT_ACCEPTABLE);
//        UserDto createduserdto  =authService.createdCustomer(signupRequest);
//        if(createduserdto==null) return new ResponseEntity<>(
//                "customer not created", HttpStatus.BAD_REQUEST
//        );
//        return new ResponseEntity<>(createduserdto,HttpStatus.CREATED);
//
//    }
//
//    @PostMapping("/login")
//    public AuthenticationResponse createauthenticationtoken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException, DisabledException, UsernameNotFoundException, BadRequestException {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
//        }catch(BadCredentialsException e){
//            throw new BadRequestException("incorrect username or passoword");
//        }
//        final UserDetails userDetails= userService.userDetailService().loadUserByUsername(authenticationRequest.getEmail());
//        System.out.print(userDetails.getUsername());
//        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
//        final String jwt = jwtUtill.generateToken(userDetails);
//        AuthenticationResponse  authenticationResponse= new AuthenticationResponse();
//        if(optionalUser.isPresent()){
//            authenticationResponse.setJwt(jwt);
//            authenticationResponse.setUserId(optionalUser.get().getId());
//            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
//        }
//        return authenticationResponse;
//
//    }
//}
