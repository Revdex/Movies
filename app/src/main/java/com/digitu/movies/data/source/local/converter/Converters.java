package com.digitu.movies.data.source.local.converter;

import android.arch.persistence.room.TypeConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Converters {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static List<Long> toList(String str) {
        List<Long> list;
        try {
            list = new ObjectMapper().readValue(str, new TypeReference<List<Long>>() {
            });
        } catch (IOException error) {
            list = null;
        }
        return list;
    }

    @TypeConverter
    public static String toString(List<Long> list) {
        String decoded;
        if (list != null) {
            try {
                decoded = new ObjectMapper().writeValueAsString(list);
            } catch (IOException e) {
                decoded = null;
            }
        } else {
            decoded = null;
        }
        return decoded;
    }
}
