package ru.sber.beautifulcode.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Beautiful Code Api",
                description = "Beautiful Code", version = "1.0.0",
                contact = @Contact(
                        name = "Kostyrev Alexandr",
                        email = "killa0007@mail.ru"
                )
        )
)
public class OpenApiConfig {
}
