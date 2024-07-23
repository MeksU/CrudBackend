package pl.meksu.campaignrud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.meksu.campaignrud.controller.CampaignController;
import pl.meksu.campaignrud.dto.CampaignDTO;
import pl.meksu.campaignrud.exception.DataNotFoundException;
import pl.meksu.campaignrud.exception.InsufficientFundsException;
import pl.meksu.campaignrud.model.Campaign;
import pl.meksu.campaignrud.model.Town;
import pl.meksu.campaignrud.model.User;
import pl.meksu.campaignrud.repository.CampaignRepository;
import pl.meksu.campaignrud.repository.UserRepository;
import pl.meksu.campaignrud.service.CampaignService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CampaignrudApplicationTests {

	@Test
	void contextLoads() { }
}

class CampaignControllerTest {

	@Mock
	private CampaignService campaignService;

	@InjectMocks
	private CampaignController campaignController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getCampaignById() {
		Long campaignId = 1L;
		Campaign campaign = new Campaign();
		when(campaignService.getCampaignById(campaignId)).thenReturn(campaign);

		ResponseEntity<Campaign> result = campaignController.getCampaignById(campaignId);

		assertEquals(ResponseEntity.ok(campaign), result);
	}

	@Test
	void getUsersCampaigns() {
		Long userId = 1L;
		List<Campaign> campaigns = new ArrayList<>();
		campaigns.add(new Campaign());
		when(campaignService.getCampaignsByUserId(userId)).thenReturn(campaigns);

		ResponseEntity<List<Campaign>> result = campaignController.getUsersCampaigns(userId);

		assertEquals(ResponseEntity.ok(campaigns), result);
	}

	@Test
	void createCampaign() {
		CampaignDTO campaignDTO = new CampaignDTO();
		Campaign campaign = new Campaign();
		when(campaignService.createCampaign(campaignDTO)).thenReturn(campaign);

		ResponseEntity<Campaign> result = campaignController.createCampaign(campaignDTO);

		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertEquals(campaign, result.getBody());
	}

	@Test
	void updateCampaign() {
		Long campaignId = 1L;
		CampaignDTO campaignDTO = new CampaignDTO();
		Campaign campaign = new Campaign();
		when(campaignService.updateCampaign(campaignId, campaignDTO)).thenReturn(campaign);

		ResponseEntity<Campaign> result = campaignController.updateCampaign(campaignId, campaignDTO);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(campaign, result.getBody());
	}
}

class CampaignServiceTest {

	@Mock
	private CampaignRepository campaignRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private CampaignService campaignService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getCampaignByIdNotFound() {
		Long campaignId = 1L;
		when(campaignRepository.findById(campaignId)).thenReturn(Optional.empty());

		assertThrows(DataNotFoundException.class, () -> campaignService.getCampaignById(campaignId));
	}

	@Test
	void createCampaignInsufficientFunds() {
		Long userId = 1L;
		CampaignDTO campaignDTO = new CampaignDTO("Test Campaign", "keywords", 10.0, 300.0, true, Town.LIVERPOOL, 5, userId);

		User user = new User();
		user.setFunds(200.0);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		assertThrows(InsufficientFundsException.class, () -> campaignService.createCampaign(campaignDTO));
	}

	@Test
	void deleteCampaignNotFound() {
		Long campaignId = 1L;
		when(campaignRepository.existsById(campaignId)).thenReturn(false);

		assertThrows(DataNotFoundException.class, () -> campaignService.deleteCampaign(campaignId));
	}
}