package com.springboot.rest;

import com.springboot.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class RabbitContrlol {

    @Autowired
    RabbitService rabbitService;

    //@PostMapping("/msg")
    @GetMapping("/msg")
    public String tomsg(@RequestParam("msg") String msg){

        rabbitService.SendMsg(msg);
        return "sucess";
    }
}
