package com.tastemap.taste_map.domain.member.repository;

import com.tastemap.taste_map.domain.member.entity.Member;
import com.tastemap.taste_map.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
