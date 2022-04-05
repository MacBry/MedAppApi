package pl.mac.bry.referral_unit;

import lombok.*;
import pl.mac.bry.unit_address.UnitAddress;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferralUnit implements Serializable {

    private static final long serialVersionUID = 1548618989609648406L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    private String shortName;

    private long nipNumber;

    private long regonNumber;

    private String email;

    private String resortBookNumber;

    @OneToOne
    private UnitAddress unitAddress;
}
