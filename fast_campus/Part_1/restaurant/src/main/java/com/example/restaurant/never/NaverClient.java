package com.example.restaurant.never;

import com.example.restaurant.never.dto.SearchImageReq;
import com.example.restaurant.never.dto.SearchImageRes;
import com.example.restaurant.never.dto.SearchLocalReq;
import com.example.restaurant.never.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value("${spring.output.ansi.enabled}")
    private String enabled;

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;


    public SearchLocalRes localSearch(SearchLocalReq searchLocalReq){
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())    /* 한번에 값 집어넣기 */
                .build()
                .encode()
                .toUri();


         var headers = new HttpHeaders();
         headers.set("X-Naver-Client-Id",naverClientId);
         headers.set("X-Naver-Client-Secret",naverClientSecret);
         headers.setContentType(MediaType.APPLICATION_JSON);

         var httpEntity = new HttpEntity<>(headers);
         var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};

         var responseEntity = new RestTemplate().exchange(
                 uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
    return responseEntity.getBody();
    }
    public SearchImageRes searchImage(SearchImageReq searchImageReq){
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())    /* 한번에 값 집어넣기 */
                .build()
                .encode()
                .toUri();


        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id",naverClientId);
        headers.set("X-Naver-Client-Secret",naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseEntity.getBody();
    }
}
