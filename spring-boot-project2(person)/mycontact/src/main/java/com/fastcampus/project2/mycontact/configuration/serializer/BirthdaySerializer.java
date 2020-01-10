package com.fastcampus.project2.mycontact.configuration.serializer;

import com.fastcampus.project2.mycontact.domain.dto.Birthday;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

public class BirthdaySerializer extends JsonSerializer<Birthday> {
    @Override
    public void serialize(Birthday value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value!=null)
            gen.writeObject(LocalDate.of(value.getYearOfBirthday(),value.getMonthOfBirthday(),value.getDayOfBirthday()));
    }
}
