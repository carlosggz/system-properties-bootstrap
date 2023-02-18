package com.example.systemproperty;

import com.example.systemproperty.bootstrap.SystemPropertiesBootstrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
public class SystemPropertyApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(SystemPropertyApplication.class)
            .listeners(new SystemPropertiesBootstrap())
            .run(args);
    }
}
