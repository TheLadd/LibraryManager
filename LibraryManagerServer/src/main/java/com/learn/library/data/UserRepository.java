package com.learn.library.data;

import com.learn.library.model.Member;
import com.learn.library.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findByUserId(int memberId);
}
