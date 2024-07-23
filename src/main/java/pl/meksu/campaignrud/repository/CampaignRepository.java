package pl.meksu.campaignrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.meksu.campaignrud.model.Campaign;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByUserId(Long userId);
}