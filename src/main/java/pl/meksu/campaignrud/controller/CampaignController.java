package pl.meksu.campaignrud.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meksu.campaignrud.dto.CampaignDTO;
import pl.meksu.campaignrud.model.Campaign;
import pl.meksu.campaignrud.service.CampaignService;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@CrossOrigin("http://localhost:3000")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Campaign>> getUsersCampaigns(@PathVariable Long id) {
        List<Campaign> campaigns = campaignService.getCampaignsByUserId(id);
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        Campaign campaign = campaignService.getCampaignById(id);
        return ResponseEntity.ok(campaign);
    }

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@Valid @RequestBody CampaignDTO campaign) {
        Campaign createdCampaign = campaignService.createCampaign(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCampaign);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long id, @Valid @RequestBody CampaignDTO campaign) {
        Campaign updatedCampaign = campaignService.updateCampaign(id, campaign);
        return ResponseEntity.ok(updatedCampaign);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
}