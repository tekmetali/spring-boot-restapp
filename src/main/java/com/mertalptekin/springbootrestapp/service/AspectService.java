package com.mertalptekin.springbootrestapp.service;

import com.mertalptekin.springbootrestapp.aspects.Log;
import org.springframework.stereotype.Service;

@Service
public class AspectService {

    @Log // Anatasyon kullanımı ile aspect tetiklenir
    public  void execute() {
        throw new RuntimeException("hata oluştu");
        // System.out.println("execute");
    }
}

