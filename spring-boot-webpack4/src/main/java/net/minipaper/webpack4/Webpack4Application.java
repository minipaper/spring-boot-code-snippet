package net.minipaper.webpack4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Webpack4Application {

  public static void main(String[] args) {
    SpringApplication.run(Webpack4Application.class, args);
  }

  // Enable CORS globally
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        // frontend for dev
        registry.addMapping("/api/**").allowedOrigins("http://localhost:8081");
      }
    };
  }


}
