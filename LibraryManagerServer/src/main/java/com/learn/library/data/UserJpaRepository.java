package com.learn.library.data;

import com.learn.library.model.Member;
import com.learn.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*

    NOTES:
    - A. This interface is effectively a class when utilized by Spring Data JPA, because it extends
         JpaRepository (don't know how; it's magic!)
    - B. This interface extends MemberRepository is used to provide loose-coupling between Spring
         Data JPA and this application
    - C. A effectively satisfies the requirement of inheritance. B satisfies the requirement of
         abstraction

 */

@Repository
public interface UserJpaRepository extends UserRepository, JpaRepository<User, Integer> {
    List<User> findAll();
    User findByUserId(int userId);
}
