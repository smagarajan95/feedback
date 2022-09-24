package com.interview.feedbackapp.service;

import com.interview.feedbackapp.entity.User;
import com.interview.feedbackapp.model.LoginInfo;
import com.interview.feedbackapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity signup(LoginInfo loginInfo) {
        if(userRepository.existsByUserName(loginInfo.getUserName()))
            return new ResponseEntity("username already exist", HttpStatus.BAD_REQUEST);
        else {
            User user = new User();
            BeanUtils.copyProperties(loginInfo, user);
            userRepository.save(user);
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    public boolean signin(LoginInfo loginInfo) {
        List<User> users = userRepository.findAll();
        List<User> user = userRepository.findByUserNameAndPassword(loginInfo.getUserName(), loginInfo.getPassword());
        return user.size() > 0;
    }

    public ResponseEntity authenticateAndRetrivePassword(LoginInfo loginInfo) {
        if(userRepository.existsByUserName(loginInfo.getUserName())){
            User user = userRepository.findByUserNameAndSecretCode(loginInfo.getUserName(), loginInfo.getSecretCode());
            if(Optional.ofNullable(user).isPresent())
                return new ResponseEntity(user.getPassword(), HttpStatus.OK);
            else
                return new ResponseEntity("Invalid secret code",HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity("Invalid user name", HttpStatus.NOT_FOUND);
    }

    public Optional<User> getUser(int userId) {
       return userRepository.findById(userId);
    }
}
