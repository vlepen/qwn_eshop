package com.garwan.eshop.rest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = ApiValidationError.class, name = "apiValidationError")})
public abstract class ApiSubError {
    public ApiSubError() {
    }
}
