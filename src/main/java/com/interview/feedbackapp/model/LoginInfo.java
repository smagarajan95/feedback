package com.interview.feedbackapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInfo {
    private String userName;
    private String password;
    private String secretCode;
}
