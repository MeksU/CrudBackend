package pl.meksu.campaignrud.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Town {
    LONDON("London"),
    MANCHESTER("Manchester"),
    LIVERPOOL("Liverpool");

    private final String name;

    Town(String name) {
        this.name = name;
    }

    @JsonCreator
    public static Town getFromValue(String value) {
        for (Town town : Town.values()) {
            if (town.name.equalsIgnoreCase(value)) {
                return town;
            }
        }
        throw new IllegalArgumentException("Invalid value for Town: " + value);
    }

    @JsonValue
    public String getValue() {
        return name;
    }

    public static List<String> getTownNames() {
        return Arrays.stream(Town.values())
                .map(Town::getName)
                .collect(Collectors.toList());
    }
}

