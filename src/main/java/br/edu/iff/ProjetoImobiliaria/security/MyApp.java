package br.edu.iff.ProjetoImobiliaria.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableAutoConfiguration
public class MyApp extends SpringBootServletInitializer { 

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(MyApp.class);
        }

        public static void main(String[] args) {
            SpringApplication.run(MyApp.class, args);
        }
}
