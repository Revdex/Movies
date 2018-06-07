package com.digitu.movies.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ParserUtils {

    private final static String ENCODING = "UTF-8";
    private final ObjectMapper objectMapper;

    public ParserUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T parseDataToStringAndStripQuote(byte[] src, Class<T> valueType) {
        T data;
        try {
            data = objectMapper.readValue(src, valueType);
        } catch (IOException error) {
            data = null;
        }
        return data;
    }

    public <T> T parseDataToStringAndStripQuote(String src, Class<T> valueType) {
        T data;
        try {
            data = objectMapper.readValue(src, valueType);
        } catch (IOException error) {
            data = null;
        }
        return data;
    }

    public <T> T parseDataToStringAndStripQuote(byte[] src, TypeReference valueTypeRef) {
        T data;
        try {
            data = objectMapper.readValue(src, valueTypeRef);
        } catch (IOException error) {
            data = null;
        }
        return data;
    }

    public String parseDataToStringAndStripQuote(byte[] src) {
        String decoded;
        try {
            decoded = new String(src, ENCODING);
            if (StringUtils.isNotEmpty(decoded) && decoded.contains("\"")) {
                decoded = decoded.replace("\"", "");
            }
        } catch (UnsupportedEncodingException e) {
            decoded = null;
        }
        return decoded;
    }

    public <T> String convertToString(T value) {
        String decoded;
        if (value != null) {
            try {
                decoded = objectMapper.writeValueAsString(value);
            } catch (IOException e) {
                decoded = null;
            }
        } else {
            decoded = null;
        }
        return decoded;
    }
}
