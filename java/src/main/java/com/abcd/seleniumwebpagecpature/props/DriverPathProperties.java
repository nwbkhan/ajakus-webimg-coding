package com.abcd.seleniumwebpagecpature.props;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.drivers")
@Data
public class DriverPathProperties {
    private String chromePath;
    private String firefoxPath;
}
