package cn.edu.bupt.springsecurity.userdetails.demo.controller;

import cn.edu.bupt.springsecurity.userdetails.demo.dto.JwtRequest;
import cn.edu.bupt.springsecurity.userdetails.demo.dto.JwtResponse;
import cn.edu.bupt.springsecurity.userdetails.demo.config.JwtTokenUtil;
import cn.edu.bupt.springsecurity.userdetails.demo.dto.MessageResponse;
import cn.edu.bupt.springsecurity.userdetails.demo.dto.UserDto;
import cn.edu.bupt.springsecurity.userdetails.demo.entity.Users;
import cn.edu.bupt.springsecurity.userdetails.demo.mapper.UsersMapper;
import cn.edu.bupt.springsecurity.userdetails.demo.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UsersMapper usersMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserService customUserService;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = customUserService
                    .loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Authentication failed: " + e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws Exception {
        Users existingUser = usersMapper.selectById(userDto.getUsername());
        if (existingUser != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Users newUser = new Users(userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()),
                    true);
            usersMapper.insert(newUser);

            // 自动登录用户并返回JWT
            authenticate(userDto.getUsername(), userDto.getPassword());
            final UserDetails userDetails = customUserService
                    .loadUserByUsername(userDto.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("Authentication failed: " + e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse("Internal Server Error: " + exception.getMessage()));
    }
}
