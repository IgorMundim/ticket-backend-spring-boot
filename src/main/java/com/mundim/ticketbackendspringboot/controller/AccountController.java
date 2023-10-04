package com.mundim.ticketbackendspringboot.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("public")
public class AccountController {
    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public ResponseEntity<String> register(){

        return  ResponseEntity.ok("Hi there");
    }
}
