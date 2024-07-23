package pl.meksu.campaignrud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "campaigns")
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String keywords;

    @Column(nullable = false)
    private Double bidAmount;

    @Column(nullable = false)
    private Double campaignFund;

    @Column(nullable = false)
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Town town;

    @Column(nullable = false)
    private Integer radius;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}