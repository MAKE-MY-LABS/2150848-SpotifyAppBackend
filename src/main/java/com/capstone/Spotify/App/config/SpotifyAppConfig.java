package com.capstone.Spotify.App.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class SpotifyAppConfig {
    /**
     *  create a configuration class
     * AppConfig to create a RestTemplate bean.
     */
    @Configuration
    public class AppConfig {
        @Bean
        public RestTemplate getRestTemplate() {
            return new RestTemplate(new SimpleClientHttpRequestFactory());
        }
    }
}
