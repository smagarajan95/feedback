package com.interview.feedbackapp.controller;
import com.interview.feedbackapp.model.FeedbackModel;
import com.interview.feedbackapp.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("add")
    public ResponseEntity addNewFeedback(@RequestBody FeedbackModel feedbackModel){
        return feedbackService.addNewFeedback(feedbackModel);
    }

    @GetMapping("getAll")
    public ResponseEntity getAllFeedback(@RequestParam(required = false) Integer userId){
        return feedbackService.getAllFeedback(userId);
    }
}
