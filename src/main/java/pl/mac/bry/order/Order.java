package pl.mac.bry.order;

import lombok.*;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.referral_unit.ReferralUnit;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;


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

    private ZonedDateTime orderDateTime;

    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private ReferralUnit referralUnit;

}
