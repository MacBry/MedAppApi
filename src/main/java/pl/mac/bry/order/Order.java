package pl.mac.bry.order;

import lombok.*;
import pl.mac.bry.lab_test.LabTest;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.referral_unit.ReferralUnit;


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
    private Patient patient;

    private ZonedDateTime orderDateTime;

    private OrderStatus orderStatus;

    @ManyToOne
    private ReferralUnit referralUnit;

    @ManyToMany
    private Set<LabTest> labTests;

    public void addLabTest(LabTest labTest){
        labTests.add(labTest);
    }
}
