package com.stclub.web.core.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MinioConfig{
    private String endpoint;
    private String accessKey;
    private String secretKey;

    @Bean
    public MinioClient getMinioClient() throws InvalidEndpointException, InvalidPortException {
        MinioClient minioClient = MinioClient.builder().endpoint("http://169.254.68.125:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
        return minioClient;
    }
}

