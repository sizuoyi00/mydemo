package com.jincou.ocontroller;


import com.jincou.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SampleController {


    @GetMapping("/testjackson")
    public Object testjson(){

        return new User(0, "密码", null, new Date());
    }




}