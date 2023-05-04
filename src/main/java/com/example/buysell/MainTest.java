package com.example.buysell;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class MainTest {

    public static void main(String[] args) {

    }

    public static String rgb(int r, int g, int b) {
        List<Integer> list = List.of(r, g, b).stream().map(num -> Math.min(Math.max(num, 0), 255)).collect(Collectors.toList());
        String hexString = list.stream().map(num -> {
            String hex = Integer.toHexString(num);
            if (hex.matches("[0-9]")) {
                return "0" + hex;
            } else return hex.toUpperCase();
        }).collect(Collectors.joining(""));
        return hexString;

    }

    @Test
    public void sampleTests() {
        assertEquals("For input: (0, 0, 0)", "000000", MainTest.rgb(0, 0, 0));
        assertEquals("For input: (1, 2, 3)", "010203", MainTest.rgb(1, 2, 3));
        assertEquals("For input: (255, 255, 255)", "FFFFFF", MainTest.rgb(255, 255, 255));
        assertEquals("For input: (254, 253, 252)", "FEFDFC", MainTest.rgb(254, 253, 252));
        assertEquals("For input: (-20, 275, 125)", "00FF7D", MainTest.rgb(-20, 275, 125));
    }
}
