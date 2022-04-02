package pl.mac.bry.order;

import lombok.*;
import pl.mac.bry.address.Address;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.referralUnit.ReferralUnit;
import pl.mac.bry.test.Test;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {

    private static final long serialVersionUID = -7764548738370232183L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private Address patientAddress = patient.getAddresses().stream().findFirst().get();

    private ZonedDateTime orderDateTime;

    private ReferralUnit referralUnit;

    private Set<Test> tests;
}
