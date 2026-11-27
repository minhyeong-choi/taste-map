package com.tastemap.taste_map.domain.place.repository;

import com.tastemap.taste_map.domain.place.entity.Place;
import com.tastemap.taste_map.domain.place.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceImageRepository extends JpaRepository<PlaceImage, Long> {

    List<PlaceImage> findByPlace(Place place);
}
