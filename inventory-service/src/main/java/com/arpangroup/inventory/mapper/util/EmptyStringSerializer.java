package com.arpangroup.inventory.mapper.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmptyStringSerializer extends JsonSerializer<Object> {

//    public static EmptyStringSerializer INSTANCE;
//
//    public EmptyStringSerializer(){
//        super();
//        if (INSTANCE ==null){
//            INSTANCE = new EmptyStringSerializer();
//        }
//    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString("");
    }
}

