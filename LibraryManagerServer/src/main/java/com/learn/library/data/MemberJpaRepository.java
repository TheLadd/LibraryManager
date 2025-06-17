package com.learn.library.data;

import com.learn.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*

    NOTES:
    - This interface is effectively a class when utilized by Spring Data JPA (don't know how; it's magic!)
    - The MemberRepository is used to provide loose-coupling between Spring Data JPA and
      this application

 */

@Repository
public interface MemberJpaRepository extends MemberRepository, JpaRepository<Member, Integer> {
    List<Member> findAll();
    Member findByMemberId(int memberId);
}
