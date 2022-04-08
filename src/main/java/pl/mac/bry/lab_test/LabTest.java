package pl.mac.bry.lab_test;

import lombok.*;
import pl.mac.bry.sample.Sample;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabTest implements Serializable {

    private static final long serialVersionUID = -3587722575968935452L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String labTestName;

    private String alterTestName;

    private int upperReferenceValue;

    private int lowerReferenceValue;

    private int result;

    @OneToOne
    private Sample sample;
}
