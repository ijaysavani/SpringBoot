package com.jaysavani.webapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class helloWorldController {


    @ResponseBody
    @RequestMapping("say-hello")
    public String sayHello() {
        return "Hi how are you ?";
    }


    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }
}
