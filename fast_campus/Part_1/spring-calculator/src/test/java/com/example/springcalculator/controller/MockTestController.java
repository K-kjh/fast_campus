package com.example.springcalculator.controller;

import com.example.springcalculator.Controller.CalController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalController.class)
@AutoConfigureWebMvc
public class MockTestController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void dollTest(){
        // Mockito.when(markeApi.connect()).thenReturn(3000);
    }

    @Test
    public void aTest()  throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x","10")
                        .queryParam("y","20")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("30")   //출력값
        ).andDo(MockMvcResultHandlers.print());

    }


//    @Test
//    public void sumTest() {
//        Req req = new Req();
//        req.setX(10);
//        req.setX(10);
//
//        String json = new ObjectMapper().wiriteValueAsString();
//     mockMvc.perform(
//            MockMvcRequestBuilders.post("url")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json)
//     ).andExpect(
//             MockMvcResultMatchers.status().isOK()
//     ).andExpect(
//             MockMvcResultMatchers.jsonPath("$.result").value("0") // 기대값 추가 . 이 값이 틀리면 안된다.
//     ).andExpect(
//                                            /* 작성 예) .변수명.변수명  = $.객체.객체*/
//             MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
//     ).andDO(MockMvcResultHandlers.print());
//
//    }


}
