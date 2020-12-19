package cn.edu.buaa.scholarshipserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileUploadingConfig implements WebMvcConfigurer {
    @Value("${files.path}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pictures/**").addResourceLocations("file:" + filePath);
        registry.addResourceHandler("/files/**").addResourceLocations("file:" + filePath);
    }

}
