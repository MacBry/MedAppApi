package pl.mac.bry.lab_test;

import org.springframework.stereotype.Service;

@Service
public class LabTestDtoMapper {

    LabTestDto map (LabTest labTest) {
        return LabTestDto.builder()
                .id(labTest.getId())
                .labTestName(labTest.getLabTestName())
                .alterTestName(labTest.getAlterTestName())
                .upperReferenceValue(labTest.getUpperReferenceValue())
                .lowerReferenceValue(labTest.getLowerReferenceValue())
                .result(labTest.getResult())
                .build();
    }

    LabTest map (LabTestDto labTestDto) {
        return LabTest.builder()
                .labTestName(labTestDto.getLabTestName())
                .alterTestName(labTestDto.getAlterTestName())
                .upperReferenceValue(labTestDto.getUpperReferenceValue())
                .lowerReferenceValue(labTestDto.getLowerReferenceValue())
                .result(labTestDto.getResult())
                .build();
    }
}
