package com.elevenst.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;

@Repository
public class ProductRepository implements Repository {

    @Override
    public String value() {
        return "value";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
