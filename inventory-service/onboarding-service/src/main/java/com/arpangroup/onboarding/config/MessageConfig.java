package com.arpangroup.onboarding.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class MessageConfig {
    //https://www.baeldung.com/spring-custom-validation-message-source
    //https://zetcode.com/spring/messagesource/

    @Value("${onboard.errorcodes.file.name}")
    private String errorCodesFile;

    /*@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/error", "messages/label");

        return messageSource;

//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
    }*/



    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames(
//                "classpath:messages/messages",
//                "file:E:/projects/git/food-ordering-services/inventory-service/config/errors");

        String errorFileName = errorCodesFile.substring(0, errorCodesFile.lastIndexOf("."));


        messageSource.setBasenames(
                "file:" + errorFileName,
                "classpath:messages/messages",
                "classpath:messages/errors"
        );
//        messageSource.setCacheSeconds(600);
//        messageSource.setUseCodeAsDefaultMessage(true);
//        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }


    /*@Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }*/

}
