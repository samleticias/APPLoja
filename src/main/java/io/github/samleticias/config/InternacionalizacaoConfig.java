package io.github.samleticias.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

// Interpolação: leitura do arquivo com chaves de mensagens personalizadas para validações
@Configuration
public class InternacionalizacaoConfig {
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // messages.properties
        messageSource.setDefaultEncoding("ISO-8859-1"); // reconhecer codificação de texto
        messageSource.setDefaultLocale(Locale.getDefault()); // local padrão de onde está rodando a aplicação
        return messageSource;
    }

    // interpolação para validar campos obrigatórios
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
