package com.example.springcalculator;

import com.example.springcalculator.component.MarkeApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@Import({MarkeApi.class})
public class DollTest {

    @MockBean
    private MarkeApi markeApi;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void dollTest(){
       // Mockito.when(markeApi.connect()).thenReturn(3000);
    }
}
