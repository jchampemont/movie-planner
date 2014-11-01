package com.jeanchampemont.movieplanner.utils;

import com.jeanchampemont.movieplanner.Application;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DurationUtilsTest {
    @Autowired
    private DurationUtils sut;

    @Test
    public void testParseEmpty() {
        Duration d = Duration.ofHours(0).plusMinutes(0);
        assertEquals(d, sut.parse(""));
    }
    @Test
    public void testParseSimple() {
        Duration d = Duration.ofHours(12).plusMinutes(43);
        assertEquals(d, sut.parse("12h43min"));
    }
    @Test
    public void testParseZeroHours() {
        Duration d = Duration.ofHours(0).plusMinutes(2);
        assertEquals(d, sut.parse("0h02min"));
    }
    @Test
    public void testParseEmptyHours() {
        Duration d = Duration.ofHours(0).plusMinutes(7);
        assertEquals(d, sut.parse("7min"));
    }

    @Test
    public void testPrintSimple() {
        String s = "1h43min";
        assertEquals(s, sut.print(Duration.ofHours(1).plusMinutes(43)));
    }

    @Test
    public void testPrintZero() {
        String s = "0h43min";
        assertEquals(s, sut.print(Duration.ofHours(0).plusMinutes(43)));
    }
}
