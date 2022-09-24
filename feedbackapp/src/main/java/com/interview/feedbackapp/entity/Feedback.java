package com.interview.feedbackapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "FEEDBACK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int feedbackId;

    private String feedback;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Feedback(String feedback, User user) {
        this.feedback = feedback;
        this.user = user;
    }
}
