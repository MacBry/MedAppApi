package pl.mac.bry.unit_address;

import lombok.*;
import pl.mac.bry.address.Address;
import pl.mac.bry.address.StreetPrefix;
import pl.mac.bry.referral_unit.ReferralUnit;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UnitAddress extends Address implements Serializable {

    private static final long serialVersionUID = 1147372049381444226L;

    @OneToOne
    @JoinColumn(name="unit_id")
    private ReferralUnit referralUnit;

    @Builder //constructor for builder
    public UnitAddress(long id, String country, String city,
                          StreetPrefix streetPrefix, String street,
                          String zipCode, String buildingNumber,
                          String apartmentNumber, ReferralUnit referralUnit) {
        super(id,country, city, streetPrefix, street, zipCode,buildingNumber ,apartmentNumber);
        this.referralUnit = referralUnit;
    }
}
