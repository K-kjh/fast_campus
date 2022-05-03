package com.example.springcalculator.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalController {

    @GetMapping("/sum")
    public int sum(@RequestParam int x,@RequestParam int y){
        return x+y;
    }

}
