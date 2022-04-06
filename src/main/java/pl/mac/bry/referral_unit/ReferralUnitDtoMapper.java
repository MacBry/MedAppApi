package pl.mac.bry.referral_unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.order.OrderFacade;

@Service
public class ReferralUnitDtoMapper {



    ReferralUnitDto map (ReferralUnit referralUnit) {
        return ReferralUnitDto.builder()
                .id(referralUnit.getId())
                .fullName(referralUnit.getFullName())
                .shortName(referralUnit.getShortName())
                .nipNumber(String.valueOf(referralUnit.getNipNumber()))
                .regonNumber(String.valueOf(referralUnit.getRegonNumber()))
                .email(referralUnit.getEmail())
                .resortBookNumber(referralUnit.getResortBookNumber())
                .build();
    }

    ReferralUnit map (ReferralUnitDto dto) {
        return ReferralUnit.builder()
                .fullName(dto.getFullName())
                .shortName(dto.getShortName())
                .nipNumber(Long.parseLong(dto.getNipNumber()))
                .regonNumber(Long.parseLong(dto.getRegonNumber()))
                .email(dto.getEmail())
                .resortBookNumber(dto.getResortBookNumber())
                .build();

    }
}
