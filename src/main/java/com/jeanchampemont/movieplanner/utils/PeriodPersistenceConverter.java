package com.jeanchampemont.movieplanner.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.Period;
import java.util.Objects;

@Converter(autoApply = true)
public class PeriodPersistenceConverter implements AttributeConverter<Duration, String> {

    /**
     * @return an ISO-8601 representation of this duration.
     * @see Period#toString()
     */
    @Override
    public String convertToDatabaseColumn(Duration entityValue) {
        return Objects.toString(entityValue, null);
    }

    @Override
    public Duration convertToEntityAttribute(String databaseValue) {
        return Duration.parse(databaseValue);
    }
}
