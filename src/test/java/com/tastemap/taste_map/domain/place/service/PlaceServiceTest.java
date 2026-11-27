package com.tastemap.taste_map.domain.place.service;

import com.tastemap.taste_map.domain.member.entity.Member;
import com.tastemap.taste_map.domain.place.dto.PlaceRequestDto;
import com.tastemap.taste_map.domain.place.dto.PlaceResponseDto;
import com.tastemap.taste_map.domain.place.entity.Place;
import com.tastemap.taste_map.domain.place.repository.PlaceRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private PlaceService placeService;

    @Test
    @DisplayName("맛집을 등록하면 ID를 반환한다")
    void createPlace() {
        // given
        Member member = Member.builder()
                .email("test@test.com")
                .nickname("테스트")
                .build();

        PlaceRequestDto dto = new PlaceRequestDto();

        Place savedPlace = Place.builder()
                .name("맛있는 짬뽕")
                .member(member)
                .build();
        given(placeRepository.save(any(Place.class))).willReturn(savedPlace);

        // when
        Long placeId = placeService.createPlace(member, dto);

        // then
        verify(placeRepository).save(any(Place.class));
    }

    @Test
    @DisplayName("ID로 맛집을 조회하면 상세 정보를 반환한다")
    void getPlace_success(){
        // given
        Place place = Place.builder()
                .name("맛있는 짬뽕")
                .address("서울시 강남구")
                .build();
        given(placeRepository.findById(1L)).willReturn(Optional.of(place));

        // when
        PlaceResponseDto result = placeService.getPlace(1L);

        // then
        assertThat(result.getName()).isEqualTo("서울시 강남구");
    }

    @Test
    @DisplayName("존재하지 않는 맛집을 조회하면 예외가 발생한다")
    void getPlace_notFound() {

        // given
        given(placeRepository.findById(999L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> placeService.getPlace(999L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}