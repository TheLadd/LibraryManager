package com.learn.library.domain;

import com.learn.library.data.MemberJpaRepository;
import com.learn.library.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberJpaRepository memberJpaRepository;


    public MemberService(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    public List<Member> findAll() {
        return memberJpaRepository.findAll();
    }
}
