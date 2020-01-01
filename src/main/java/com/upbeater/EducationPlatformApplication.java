package com.upbeater;

import com.upbeater.model.auxiliary.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class EducationPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationPlatformApplication.class, args);
    }

}
