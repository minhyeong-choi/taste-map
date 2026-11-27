package com.tastemap.taste_map.domain.place.service;

import com.tastemap.taste_map.domain.member.entity.Member;
import com.tastemap.taste_map.domain.place.dto.PlaceRequestDto;
import com.tastemap.taste_map.domain.place.dto.PlaceResponseDto;
import com.tastemap.taste_map.domain.place.entity.Place;
import com.tastemap.taste_map.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public Long createPlace(Member member, PlaceRequestDto dto) {

        Place place = dto.toEntity(member);
        Place savedPlace = placeRepository.save(place);

        return savedPlace.getId();
    }

    public List<PlaceResponseDto> getMyPlaces(Member member) {
        return placeRepository.findByMemberOrderByCreatedAtDesc(member).stream().map(PlaceResponseDto::new).toList();
    }

    public List<PlaceResponseDto> getPublicPlaces() {
        return placeRepository.findByIsPublicTrueOrderByCreatedAtDesc().stream().map(PlaceResponseDto::new).toList();
    }

    public PlaceResponseDto getPlace(Long id) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("맛집을 찾을 수 없습니다."));

        return new PlaceResponseDto(place);
    }

    @Transactional
    public Long updatePlace(Long id, Member member, PlaceRequestDto dto) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("맛집을 찾을 수 없습니다."));

        if (!place.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("본인의 맛집만 수정할 수 있습니다.");
        }

        place.update(dto);
        return place.getId();
    }

    @Transactional
    public void deletePlace(Long id, Member member) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("맛집을 찾을 수 없습니다."));

        if (!place.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("본인의 맛집만 삭제할 수 있습니다.");
        }

        placeRepository.delete(place);
    }
}

