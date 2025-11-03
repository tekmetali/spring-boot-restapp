package com.mertalptekin.springbootrestapp._demo.springContext.logger;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Not: @Service,@Controller,@Repository dışında bir hizmet ise @Component kullanabiliriz. @Component genel bir stereotype bean tanımıdır.
// Qualifier ile hangi bean ın kullanılacağını belirleyebiliriz.

@Component("textLogger") // Bean ismi ILogger olarak tanımlandı
@Primary
public class TextLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.println("TextLogger : " + message);
    }
}
