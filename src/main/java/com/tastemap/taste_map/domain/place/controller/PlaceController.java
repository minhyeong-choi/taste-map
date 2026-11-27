package com.tastemap.taste_map.domain.place.controller;

import com.tastemap.taste_map.domain.member.entity.Member;
import com.tastemap.taste_map.domain.member.repository.MemberRepository;
import com.tastemap.taste_map.domain.place.dto.PlaceRequestDto;
import com.tastemap.taste_map.domain.place.dto.PlaceResponseDto;
import com.tastemap.taste_map.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService;
    private final MemberRepository memberRepository;

    //TODO: 카카오 로그인 구현 후 교체
    private Member getCurrentMember() {
        return memberRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }


    @PostMapping
    public ResponseEntity<Long> createPlace(@RequestBody PlaceRequestDto dto) {
        Long placeId = placeService.createPlace(getCurrentMember(), dto);
        return ResponseEntity.ok(placeId);
    }

    @GetMapping("/my")
    public ResponseEntity<List<PlaceResponseDto>> getMyPlaces () {
        return ResponseEntity.ok(placeService.getMyPlaces(getCurrentMember()));
    }

    @GetMapping("/public")
    public ResponseEntity<List<PlaceResponseDto>> getPublicPlaces () {
        return ResponseEntity.ok(placeService.getPublicPlaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponseDto> getPlace(@PathVariable Long id){
        return ResponseEntity.ok(placeService.getPlace(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePlace(@PathVariable Long id, @RequestBody PlaceRequestDto dto) {
        return ResponseEntity.ok(placeService.updatePlace(id, getCurrentMember(), dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id, getCurrentMember());
        return ResponseEntity.ok().build();
    }
}
