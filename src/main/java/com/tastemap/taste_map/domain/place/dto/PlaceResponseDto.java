package com.tastemap.taste_map.domain.place.dto;

import com.tastemap.taste_map.domain.member.entity.Member;
import com.tastemap.taste_map.domain.place.entity.Place;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlaceResponseDto {
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String category;
    private String memo;
    private Integer rating;
    private Boolean isPublic;
    private LocalDateTime createdAt;

    public PlaceResponseDto(Place place) {
        this.id = place.getId();
        this.name = place.getName();
        this.address = place.getAddress();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.category = place.getCategory();
        this.memo = place.getMemo();
        this.rating = place.getRating();
        this.isPublic = place.getIsPublic();
        this.createdAt = place.getCreatedAt();
    }
}
