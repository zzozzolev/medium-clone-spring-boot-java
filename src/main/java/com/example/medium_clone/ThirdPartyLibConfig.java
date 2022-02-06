package com.example.medium_clone;

import com.github.slugify.Slugify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThirdPartyLibConfig {

    @Bean
    public Slugify getSlugify() {
        return new Slugify();
    }
}
