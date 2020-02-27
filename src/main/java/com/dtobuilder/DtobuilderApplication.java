package com.dtobuilder;

import com.dtobuilder.sender.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DtobuilderApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DtobuilderApplication.class, args);
    }

}
