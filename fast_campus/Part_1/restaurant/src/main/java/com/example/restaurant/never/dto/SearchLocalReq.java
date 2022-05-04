package com.example.restaurant.never.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalReq {
    private String query="";
    private int display=1;
    private int start=1;
    private String sort="random";

    public MultiValueMap<String,String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String,String>();
            map.add("query", query);
            map.add("display", String.valueOf(display));
            map.add("start", String.valueOf(start));
            map.add("sort",sort);
        // 맵으로 넣으므로써 uri 부분에서 넣어줄 필요가 없어졋다.
        return map;
    }
}
