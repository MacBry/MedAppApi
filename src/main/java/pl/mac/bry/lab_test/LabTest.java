package pl.mac.bry.lab_test;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.ZonedDateTime;

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

}
