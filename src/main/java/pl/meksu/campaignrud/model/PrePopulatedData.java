package pl.meksu.campaignrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrePopulatedData {
    private List<String> towns;
    private String keywords;
}
