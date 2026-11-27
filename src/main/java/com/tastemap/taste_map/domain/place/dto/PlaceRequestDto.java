package com.tastemap.taste_map.domain.place.dto;

import com.tastemap.taste_map.domain.member.entity.Member;
import com.tastemap.taste_map.domain.place.entity.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 클라이언트가 맛집을 등록/수정할 때 보내는 데이터
 */
@Getter
@NoArgsConstructor
public class PlaceRequestDto {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String category;
    private String memo;
    private Integer rating;
    private Boolean isPublic;

    public Place toEntity(Member member) {
        return Place.builder()
                .member(member)
                .name(name)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .category(category)
                .memo(memo)
                .rating(rating)
                .isPublic(isPublic)
                .build();
    }
}
