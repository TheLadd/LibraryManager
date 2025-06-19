package com.learn.library.domain;

import com.learn.library.data.UserJpaRepository;
import com.learn.library.model.Member;
import com.learn.library.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserJpaRepository memberJpaRepository;


    public UserService(UserJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    public List<User> findAll() {
        return memberJpaRepository.findAll();
    }
}
