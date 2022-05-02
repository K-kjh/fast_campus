package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    //localhost:8080/api/server/hello
    //response 을 사용함

    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server") /* 원하는 패스 */
                //.queryParam("name","kang")   url 파라미터 값을 넣어줄수 있다.
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result= restTemplate.getForEntity(uri,UserResponse.class);

        String src= restTemplate.getForObject(uri, String.class);
        log.info(src);

        return  result.getBody();
    }

    public  void post(){
        //http://localhost:9090/api/server/user
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("100","steve")
                .toUri();

        System.out.println("uri = " + uri);
        //http body -> object -> object mapper -> json -> rest template -> http body json

        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        //rest template 으로 쏘기만 하면 된다.
        RestTemplate restTemplate = new RestTemplate();
        //해당 주소에 req 바디를 만들어 응답을 이걸로 받을거야
        ResponseEntity<UserResponse> response =  restTemplate.postForEntity(uri,req,UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        //return response.getBody();
    }

    public UserResponse exchange(){
        //http://localhost:9090/api/server/user
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("100","steve")
                .toUri();

        System.out.println("uri = " + uri);
        //http body -> object -> object mapper -> json -> rest template -> http body json

        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RequestEntity<UserRequest> requestEntity= RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","fffff")
                .body(req);

        //rest template 으로 쏘기만 하면 된다.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity,UserResponse.class);

        return response.getBody();
    }

        public Req<UserResponse> genericExchange(){
            URI uri = UriComponentsBuilder
                    .fromUriString("http://localhost:9090")
                    .path("/api/server/user/{userId}/name/{userName}")
                    .encode()
                    .build()
                    .expand("100","steve")
                    .toUri();

            System.out.println("uri = " + uri);
            //http body -> object -> object mapper -> json -> rest template -> http body json

            UserRequest userRequest = new UserRequest();
            userRequest.setName("steve");
            userRequest.setAge(10);

            Req<UserRequest> req = new Req<>();
            req.setHeader(

                new Req.Header()
            );

            req.setBody(
                    userRequest
            );

            RequestEntity<Req<UserRequest>> requestEntity= RequestEntity
                    .post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("x-authorization","abcd")
                    .header("custom-header","fffff")
                    .body(req);

            //제네릭 은 class 못함

            new ParameterizedTypeReference<UserResponse>(){};

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Req<UserResponse>> response =
                             restTemplate.exchange(requestEntity
                            , new ParameterizedTypeReference<>(){});


            return response.getBody();
        }
}
