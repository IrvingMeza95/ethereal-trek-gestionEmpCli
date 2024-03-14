package com.TeamCode.serviciousuarios.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class APIDocConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                                .title("gestionEmpCli API")
                                .version("1.0")
                                .description("Esta es la documentación oficial de la API de servicio de empleados y clientes y sus extras.")
//                        .termsOfService("http://swagger.io/terms/")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }

}
