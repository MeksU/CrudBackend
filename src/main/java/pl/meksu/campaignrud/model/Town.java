package pl.meksu.campaignrud.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Town {
    WARSAW("Warsaw"),
    KRAKOW("Krakow"),
    WROCLAW("Wroclaw");

    private final String name;

    Town(String name) {
        this.name = name;
    }

    public static List<String> getTownNames() {
        return Arrays.stream(Town.values())
                .map(Town::getName)
                .collect(Collectors.toList());
    }
}

