package com.arpangroup.inventory.mapper.util;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomSerializerProvider extends DefaultSerializerProvider {
    private static final long serialVersionUID = 1L;

    public CustomSerializerProvider() {
    }

    public CustomSerializerProvider(CustomSerializerProvider src) {
        super(src);
    }

    protected CustomSerializerProvider(SerializerProvider src, SerializationConfig config, SerializerFactory f) {
        super(src, config, f);
    }

    public DefaultSerializerProvider copy() {
        return (CustomSerializerProvider)(this.getClass() != CustomSerializerProvider.class ? super.copy() : new CustomSerializerProvider(this));
    }

    public CustomSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
        return new CustomSerializerProvider(this, config, jsf);
    }


    @Override
    public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
        if (property.getType().getRawClass().equals(String.class)) {
            //return EmptyStringSerializer.INSTANCE;
//            return new EmptyStringSerializer();
            return super.findNullValueSerializer(property);
        } else {
            return super.findNullValueSerializer(property);
        }
    }

}
