package com.interview.feedbackapp.service;

import com.interview.feedbackapp.entity.Feedback;
import com.interview.feedbackapp.entity.User;
import com.interview.feedbackapp.model.FeedbackModel;
import com.interview.feedbackapp.repository.FeedbackRepository;
import com.interview.feedbackapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private LoginService loginService;

    public ResponseEntity addNewFeedback(FeedbackModel feedbackModel) {
        Optional<User> optionalUser = loginService.getUser(feedbackModel.getUserId());
        if(optionalUser.isEmpty())
            return new ResponseEntity("Invalid User Id", HttpStatus.BAD_REQUEST);
        else {
            Feedback feedback = new Feedback(feedbackModel.getFeedback(),optionalUser.get());
            feedbackRepository.save(feedback);
        }
        return new ResponseEntity("successfully added!", HttpStatus.OK);
    }

    public ResponseEntity getAllFeedback(Integer userId) {
            List<Feedback> feedbackList;
            if (userId != null) {
                Optional<User> optionalUser = loginService.getUser(userId);
                if (optionalUser.isEmpty())
                    return new ResponseEntity("Invalid User Id", HttpStatus.BAD_REQUEST);
                feedbackList = feedbackRepository.findByUser_UserId(userId);
            } else {
                feedbackList = feedbackRepository.findAll();
            }
            return new ResponseEntity(feedbackList, HttpStatus.OK);
    }
}
