package com.teste.elotech.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
@Configuration
public class ValidationConfig {

	@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}