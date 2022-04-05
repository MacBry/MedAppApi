package pl.mac.bry.patient_address;

import lombok.*;
import pl.mac.bry.address.Address;
import pl.mac.bry.address.StreetPrefix;
import pl.mac.bry.patient.Patient;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PatientAddress extends Address implements Serializable {

    private static final long serialVersionUID = -7376389211884169304L;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Builder //constructor for builder
    public PatientAddress(long id, String country, String city,
                          StreetPrefix streetPrefix, String street,
                          String zipCode, String buildingNumber,
                          String apartmentNumber, Patient patient) {
        super(id,country, city, streetPrefix, street, zipCode,buildingNumber ,apartmentNumber);
        this.patient = patient;
    }
}
