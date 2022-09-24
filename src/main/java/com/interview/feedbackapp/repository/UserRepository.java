package com.interview.feedbackapp.repository;


import com.interview.feedbackapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserNameAndPassword(String username, String password);

    boolean existsByUserName(String userName);

    User findByUserNameAndSecretCode(String userName, String secretCode);
}
