package pl.mac.bry.lab_test;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabTestDto {
    private long id;
    private String labTestName;
    private String alterTestName;
    private int upperReferenceValue;
    private int lowerReferenceValue;
    private int result;

    private long sampleId;
}
