package com.bookshop.productms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.minio")
@Configuration("minioProperties")
public class MinioConf {

    private String url;
    private String bucket;

    //Getters and Setters go here
}
