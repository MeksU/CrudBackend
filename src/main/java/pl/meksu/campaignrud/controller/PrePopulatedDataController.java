package pl.meksu.campaignrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.meksu.campaignrud.model.PrePopulatedData;
import pl.meksu.campaignrud.model.Town;

@RestController
@RequestMapping("/api/data")
@CrossOrigin("http://localhost:3000")
public class PrePopulatedDataController {

    @GetMapping
    public ResponseEntity<PrePopulatedData> getData() {
        PrePopulatedData result = new PrePopulatedData();
        result.setTowns(Town.getTownNames());
        result.setKeywords("EXAMPLE, KEYWORD, CAMPAIGN");
        return ResponseEntity.ok(result);
    }
}

