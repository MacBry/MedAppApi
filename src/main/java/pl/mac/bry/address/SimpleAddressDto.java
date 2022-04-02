package pl.mac.bry.address;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class SimpleAddressDto {

    private Long id;
    private String country;
    private String city;
    private String streetPrefix;
    private String street;
    private String zipCode;
    private String buildingNumber;
    private String apartmentNumber;

    private Long patientId;

}
