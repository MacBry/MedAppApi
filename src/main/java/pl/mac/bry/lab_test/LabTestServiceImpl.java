package pl.mac.bry.lab_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class LabTestServiceImpl  implements LabTestService{

    private final LabTestRepository labTestRepository;
    private final LabTestDtoMapper labTestDtoMapper;

    @Autowired
    public LabTestServiceImpl(LabTestRepository labTestRepository, LabTestDtoMapper labTestDtoMapper) {
        this.labTestRepository = labTestRepository;
        this.labTestDtoMapper = labTestDtoMapper;
    }

    @Override
    public Optional<LabTestDto> findLabTestById(Long labTestId) {
        return Optional.empty();
    }

    @Override
    public Set<LabTestDto> getAllLabTest() {
        return null;
    }

    @Override
    public LabTestDto addLabTest(LabTestDto labTestDto) {
        return null;
    }

    @Override
    public Optional<LabTestDto> updateLabTest(Long labTestId, LabTestDto labTestDto) {
        return Optional.empty();
    }

    @Override
    public void deleteLabTest(Long labTestDto) {

    }
}
