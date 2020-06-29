package com.pet.mailSender.service.parsers.csvParser;

import java.io.InputStream;
import java.util.List;

public interface Parser<T> {
    List<T> getJavaObjects(InputStream inputStream, Class clazz);
}
