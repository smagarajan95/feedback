package com.interview.feedbackapp.controller;

import com.interview.feedbackapp.model.LoginInfo;
import com.interview.feedbackapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody LoginInfo loginInfo){
        return loginService.signup(loginInfo);
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody LoginInfo loginInfo){
        boolean isAuthenticated = loginService.signin(loginInfo);
        return new ResponseEntity<>(isAuthenticated, isAuthenticated ? HttpStatus.OK: HttpStatus.UNAUTHORIZED );
    }

    @PostMapping("/forgot")
    public ResponseEntity getPassword(@RequestBody LoginInfo loginInfo){
        return loginService.authenticateAndRetrivePassword(loginInfo);
    }
}
