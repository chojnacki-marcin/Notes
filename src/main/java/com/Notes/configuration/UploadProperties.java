package com.Notes.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {
    private String path;

    public String getUploadPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
