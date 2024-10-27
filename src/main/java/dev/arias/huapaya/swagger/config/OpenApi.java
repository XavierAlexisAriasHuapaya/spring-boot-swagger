package dev.arias.huapaya.swagger.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Product CRUD",
        version = "1.0.0",
        description = "This is a CRUD of products.",
        contact = @Contact(name = "Arias Huapaya Xavier Alexis",
        email = "xavieralexisariashuapaya@hotmail.com")
    )
)
public class OpenApi {
    
}
