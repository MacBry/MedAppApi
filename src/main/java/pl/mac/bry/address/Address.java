package pl.mac.bry.address;

import lombok.*;
import pl.mac.bry.patient.Patient;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Serializable {

    private static final long serialVersionUID = -7376389211884169304L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String country;

    private String city;

    private StreetPrefix streetPrefix;

    private String street;

    private String zipCode;

    private String buildingNumber;

    private String apartmentNumber;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
