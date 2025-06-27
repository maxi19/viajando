package com.viajando.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;

public class ParserTime extends TypeAdapter<LocalTime> {

    @Override
    public void write(JsonWriter out, LocalTime value) throws IOException {
        if (value != null) {
            out.value(value.toString()); // formato HH:mm:ss
        } else {
            out.nullValue();
        }
    }

    @Override
    public LocalTime read(JsonReader in) throws IOException {
        String timeStr = in.nextString();
        return LocalTime.parse(timeStr); // espera formato HH:mm:ss
    }
}