package pl.meksu.campaignrud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.meksu.campaignrud.dto.CampaignDTO;
import pl.meksu.campaignrud.exception.DataNotFoundException;
import pl.meksu.campaignrud.exception.InsufficientFundsException;
import pl.meksu.campaignrud.model.Campaign;
import pl.meksu.campaignrud.model.User;
import pl.meksu.campaignrud.repository.CampaignRepository;
import pl.meksu.campaignrud.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class CampaignService {

    private final CampaignRepository campaignRepository;

    private final UserRepository userRepository;

    public CampaignService(CampaignRepository campaignRepository, UserRepository userRepository) {
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
    }

    public List<Campaign> getCampaignsByUserId(Long userId) {
        return campaignRepository.findByUserId(userId);
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Campaign not found."));
    }

    public Campaign createCampaign(CampaignDTO campaignDTO) {
        User user = userRepository.findById(campaignDTO.getUser())
                .orElseThrow(() -> new DataNotFoundException("User not found."));;
        double campaignFund = campaignDTO.getCampaignFund();

        if (user.getFunds() >= campaignFund) {
            user.setFunds(user.getFunds() - campaignFund);
            userRepository.save(user);

            Campaign campaign = new Campaign();
            campaign.setName(campaignDTO.getName());
            campaign.setKeywords(campaignDTO.getKeywords());
            campaign.setBidAmount(campaignDTO.getBidAmount());
            campaign.setCampaignFund(campaignDTO.getCampaignFund());
            campaign.setStatus(campaignDTO.getStatus());
            campaign.setTown(campaignDTO.getTown());
            campaign.setRadius(campaignDTO.getRadius());
            campaign.setUser(user);

            return campaignRepository.save(campaign);
        } else {
            throw new InsufficientFundsException();
        }
    }

    public void deleteCampaign(Long id) {
        if (campaignRepository.existsById(id)) {
            campaignRepository.deleteById(id);
        } else {
            throw new DataNotFoundException("Campaign not found.");
        }
    }

    public Campaign updateCampaign(Long id, CampaignDTO updatedCampaign) {
        User user = userRepository.findById(updatedCampaign.getUser())
                .orElseThrow(() -> new DataNotFoundException("User not found."));

        Campaign existingCampaign = campaignRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Campaign not found."));

        double campaignFundDifference = updatedCampaign.getCampaignFund() - existingCampaign.getCampaignFund();

        if (user.getFunds() >= campaignFundDifference) {
            user.setFunds(user.getFunds() - campaignFundDifference);
            userRepository.save(user);

            existingCampaign.setName(updatedCampaign.getName());
            existingCampaign.setKeywords(updatedCampaign.getKeywords());
            existingCampaign.setBidAmount(updatedCampaign.getBidAmount());
            existingCampaign.setCampaignFund(updatedCampaign.getCampaignFund());
            existingCampaign.setStatus(updatedCampaign.getStatus());
            existingCampaign.setTown(updatedCampaign.getTown());
            existingCampaign.setRadius(updatedCampaign.getRadius());
            existingCampaign.setUser(user);

            return campaignRepository.save(existingCampaign);
        } else {
            throw new InsufficientFundsException();
        }
    }
}