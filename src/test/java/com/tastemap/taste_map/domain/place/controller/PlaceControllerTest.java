package com.tastemap.taste_map.domain.place.controller;

import com.tastemap.taste_map.domain.member.entity.Member;
import com.tastemap.taste_map.domain.member.repository.MemberRepository;
import com.tastemap.taste_map.domain.place.dto.PlaceRequestDto;
import com.tastemap.taste_map.domain.place.service.PlaceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaceControllerTest.class)
class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PlaceService placeService;

    @MockitoBean
    private MemberRepository memberRepository;

    @Test
    @DisplayName("POST /api/places 맛집 등록 성공")
    void createPlace_success() throws Exception {
        Member member = Member.builder()
                .email("test@test.com")
                .nickname("테스트")
                .build();

        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        given(placeService.createPlace(any(Member.class), any(PlaceRequestDto.class))).willReturn(1L);

        String json = objectMapper.writeValueAsString(new PlaceRequestDto());

        // when & then
        mockMvc.perform(post("/api/places")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }

}