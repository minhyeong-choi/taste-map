package com.tastemap.taste_map.domain.place.repository;

import com.tastemap.taste_map.domain.member.entity.Member;
import com.tastemap.taste_map.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    public List<Place> findByMemberOrderByCreatedAtDesc(Member member);
    public List<Place> findByIsPublicTrueOrderByCreatedAtDesc();
}

