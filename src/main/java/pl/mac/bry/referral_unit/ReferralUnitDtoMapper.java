package pl.mac.bry.referral_unit;

import org.springframework.stereotype.Service;

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
                .nipNumber(Long.valueOf(dto.getNipNumber()))
                .regonNumber(Long.valueOf(dto.getRegonNumber()))
                .email(dto.getEmail())
                .resortBookNumber(dto.getResortBookNumber())
                .build();
    }
}
