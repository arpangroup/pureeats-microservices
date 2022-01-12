package com.arpangroup.inventory.mapper.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
@Primary
public class HttpMessageConverter {

    @Bean
    @Primary
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        mapper.setSerializerProvider(new CustomSerializerProvider());
        //mapper.getSerializerProvider().setNullValueSerializer(new NullSerializer());
        jsonConverter.setObjectMapper(mapper);
        return jsonConverter;

    }
}
