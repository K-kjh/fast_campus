package com.example.restaurant.never;

import com.example.restaurant.never.dto.SearchImageReq;
import com.example.restaurant.never.dto.SearchLocalReq;
import com.example.restaurant.never.dto.YamlConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.util.List;
import java.util.Map;



@SpringBootTest
public class ClientTest {

    @Autowired
    private NaverClient naverClient;
    
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private YamlConfiguration yamlConfiguration;


    @Test
    public void localTest(){

//        @ConfigurationProperties 방법 실패
//        List<Map<String,String>> yaml = yamlConfiguration.getCilent();
//        Assertions.assertEquals("Xed0ceZCNmsLqUzazW7w",yaml.get(0).get("id"));

        var search = new SearchLocalReq();
          search.setQuery("갈비찜");

        var result = naverClient.localSearch(search);
        System.out.println("result = " + result);

//        if(applicationContext != null){
//            String[] beans = applicationContext.getBeanDefinitionNames();
//            for(String bean: beans){
//                System.out.println("bean = " + bean);
//                if(bean.equals("org.springframework.beans.factory.annotation.Value")){
//                    System.out.println("--------------------------");
//                }
//            }
//        }
        
        
    }

    @Test
    public void localImageTest(){

        var search = new SearchImageReq();
        search.setQuery("갈비찜");

        var result = naverClient.searchImage(search);
        System.out.println("result = " + result);
        Assertions.assertNotNull(result);

    }

}
