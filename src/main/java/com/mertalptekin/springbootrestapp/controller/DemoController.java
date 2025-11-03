package com.mertalptekin.springbootrestapp.controller;


import com.mertalptekin.springbootrestapp.service.AspectService;
import com.mertalptekin.springbootrestapp.springContext.custom.WebRequestBasedBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private final WebRequestBasedBean webRequestBasedBean;
    private final AspectService aspectService;

    public DemoController(WebRequestBasedBean webRequestBasedBean
    , AspectService aspectService) {
        this.aspectService = aspectService;
        this.webRequestBasedBean = webRequestBasedBean;
    }


    @GetMapping
    public String demo() {
        webRequestBasedBean.test();
        return "Demo Controller is working...";
    }

    @PostMapping
    public String demoPost() {
        aspectService.execute();
        return "Demo Controller Post is working...";
    }


}
