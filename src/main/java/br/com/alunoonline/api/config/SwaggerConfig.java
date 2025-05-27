package br.com.alunoonline.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class
SwaggerConfig {

    /*
    Vai ficar disponível  doc swagger na url: http://localhost:8080/swagger-ui/index.html
    */

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Api de Gestao Univesritaria")
                .description("Esta API permite gerencias alunos, disciplçinas, professores e matriculas.")
                .version("1.0.0")
                .build();
    }

}


