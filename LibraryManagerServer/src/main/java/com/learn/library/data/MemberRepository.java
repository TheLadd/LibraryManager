package com.learn.library.data;

import com.learn.library.model.Member;

import java.util.List;

public interface MemberRepository {
    List<Member> findAll();
    Member findByMemberId(int memberId);
}
