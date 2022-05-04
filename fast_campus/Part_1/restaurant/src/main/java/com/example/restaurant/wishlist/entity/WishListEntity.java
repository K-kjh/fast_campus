package com.example.restaurant.wishlist.entity;

import com.example.restaurant.dto.MemoryDbEntity;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishListEntity extends MemoryDbEntity {
    /*변수 역할
     * 음식명
     * 카테고리
     * 주소
     * 도로명
     * 홈페이지 주소
     * 음식 가게이미지 주소
     * 방문 여부
     * 방문 카운트
     * 마지막 방문 날짜
     */
    private String title;
    private String category;
    private String address;
    private String readAddress;
    private String homePageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCOunt;
    private LocalDateTime lastVisitDate;


}
