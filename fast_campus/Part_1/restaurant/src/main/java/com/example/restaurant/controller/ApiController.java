package com.example.restaurant.controller;

import com.example.restaurant.never.NaverClient;
import com.example.restaurant.never.dto.SearchImageReq;
import com.example.restaurant.never.dto.SearchImageRes;
import com.example.restaurant.never.dto.SearchLocalReq;
import com.example.restaurant.never.dto.SearchLocalRes;
import com.example.restaurant.wishlist.entity.WishListEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {

    @Autowired
   private NaverClient naverClient;

    String servicse;

    @GetMapping("/search")
    public SearchImageRes search(@RequestParam String search){

        var dto = new SearchImageReq();
        dto.setQuery(search);

        var result = naverClient.searchImage(dto);
        return result;
    }

    @PostMapping
    public String add(@RequestBody WishListEntity wishListDto){
        log.info("{}",wishListDto);
        return "";
    }


}
