package pl.meksu.campaignrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.meksu.campaignrud.model.Town;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class PrepopulatedDataController {

    @GetMapping
    public ResponseEntity<List<String>> getData() {
        List<String> towns = Town.getTownNames();
        return ResponseEntity.ok(towns);
    }
}

