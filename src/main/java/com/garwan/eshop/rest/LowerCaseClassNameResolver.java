package com.garwan.eshop.rest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CUSTOM;

public class LowerCaseClassNameResolver extends TypeIdResolverBase {
    private JavaType superType;

    @Override
    public void init(JavaType baseType) {
        superType = baseType;
    }

    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return CUSTOM;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {
        return context.constructSpecializedType(superType, ApiError.class);
    }
}
