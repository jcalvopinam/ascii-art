package com.jcalvopinam.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public enum Fonts {

    SANS_SERIF("SansSerif"),
    STANDARD("standard");

    private String name;

    Fonts(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<String> toList() {
        return Arrays.stream(Fonts.values()).map(Fonts::getName).collect(Collectors.toList());
    }

}
