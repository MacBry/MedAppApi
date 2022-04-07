package pl.mac.bry.lab_test;

import java.util.Optional;
import java.util.Set;

public interface LabTestService {

    Optional<LabTestDto> findLabTestById(Long labTestId);

    Set<LabTestDto> getAllLabTest();

    LabTestDto addLabTest(LabTestDto labTestDto);

    Optional<LabTestDto> updateLabTest(Long labTestId, LabTestDto labTestDto);

    void deleteLabTest(Long labTestDto);
}
