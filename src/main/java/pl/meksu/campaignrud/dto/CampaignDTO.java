package pl.meksu.campaignrud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.meksu.campaignrud.model.Town;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDTO {
    @NotEmpty(message = "Campaign name is mandatory.")
    private String name;

    @NotEmpty(message = "Keywords are mandatory.")
    private String keywords;

    @NotNull(message = "Bid amount is mandatory.")
    @Min(value = 1, message = "Bid amount must be greater than 0.")
    private Double bidAmount;

    @NotNull(message = "Campaign fund is mandatory.")
    @Min(value = 1, message = "Campaign fund must be greater than 0.")
    private Double campaignFund;

    @NotNull(message = "Status is mandatory.")
    private Boolean status;

    @NotNull(message = "Town is mandatory.")
    private Town town;

    @NotNull(message = "Radius is mandatory.")
    @Min(value = 1, message = "Radius must be greater than 0.")
    private Integer radius;

    @NotNull(message = "No user.")
    private Long user;
}
