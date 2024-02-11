package com.jaysavani.rest.webservices.restfulwebservices.helloworld;


import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class helloworldcontroller {

    private MessageSource messageSource;

    public helloworldcontroller (MessageSource messageSource){
        this.messageSource = messageSource;
    }



    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }


    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello-World");
    }

    @GetMapping(path = "/hello-world/pathvariable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello-World, %s", name));
    }


    @GetMapping( "/hello-world-internationalized")
    public String helloWorldInternationalized(){

        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);

    }



}
