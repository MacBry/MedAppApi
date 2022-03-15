package pl.mac.bry.sample;

import lombok.Data;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.sample.enums.SampleType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
public class Sample implements Serializable {

    private static final long serialVersionUID = -2656849855265974L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ZonedDateTime donationDateTime;

    private ZonedDateTime registrationDateTime;

    private SampleType sampleType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id")
    private Patient patient;
}
