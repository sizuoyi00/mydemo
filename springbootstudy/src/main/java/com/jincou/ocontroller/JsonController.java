package com.jincou.ocontroller;

import com.jincou.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class JsonController {

    @GetMapping("/jackson")
    public Object testjson(){

        return new Person("hehe","pj","123", new Date());
    }


}
