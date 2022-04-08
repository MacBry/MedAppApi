package pl.mac.bry.lab_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.sample.Sample;
import pl.mac.bry.sample.SampleFacade;

@Service
class LabTestDtoMapper {

    private final SampleFacade sampleFacade;

    @Autowired
    public LabTestDtoMapper(SampleFacade sampleFacade) {
        this.sampleFacade = sampleFacade;
    }

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
        LabTest labTest = LabTest.builder()
                .labTestName(labTestDto.getLabTestName())
                .alterTestName(labTestDto.getAlterTestName())
                .upperReferenceValue(labTestDto.getUpperReferenceValue())
                .lowerReferenceValue(labTestDto.getLowerReferenceValue())
                .result(labTestDto.getResult())
                .build();
        sampleFacade.getSampleById(labTestDto.getSampleId())
                .ifPresent(labTest::setSample);
        return  labTest;
    }
}
