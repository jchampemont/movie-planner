package com.jeanchampemont.movieplanner.utils;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DurationUtils {

    private static final Pattern durationPattern = Pattern.compile("(?:(\\d+)h)?(?:(\\d+)min)?");

    /**
     * Parse a duration with a format like "1h46min" or "2min"
     * @param duration
     * @return parsed durationu
     */
    public Duration parse(String duration) {
        Matcher m = durationPattern.matcher(duration);
        int hours = 0;
        int minutes = 0;
        if(m.matches()) {
            String hoursStr   = m.group(1);
            String minutesStr = m.group(2);
            if(!Strings.isNullOrEmpty(hoursStr)) {
                hours = Integer.parseInt(hoursStr);
            }
            if(!Strings.isNullOrEmpty(minutesStr)) {
                minutes = Integer.parseInt(minutesStr);
            }
        }
        return Duration.ofHours(hours).plusMinutes(minutes);
    }

    /**
     * Print a duration to a format like "1h46min"
     */
    public String print(Duration duration) {
        StringBuilder sb = new StringBuilder();
        sb.append(duration.toHours());
        sb.append("h");
        sb.append(duration.toMinutes() - duration.toHours() * 60);
        sb.append("min");
        return sb.toString();
    }
}
