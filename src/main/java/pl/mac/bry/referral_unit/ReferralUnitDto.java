package pl.mac.bry.referral_unit;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferralUnitDto {
    private Long id;
    private String fullName;
    private String shortName;
    private String nipNumber;
    private String regonNumber;
    private String email;
    private String resortBookNumber;

    private Long unitAddressId;
}
