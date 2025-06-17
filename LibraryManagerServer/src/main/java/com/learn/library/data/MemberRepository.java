package com.learn.library.data;

import com.learn.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findAll();
    Member findByMemberId(int memberId);
}
