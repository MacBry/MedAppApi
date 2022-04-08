package pl.mac.bry.lab_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class LabTestServiceImpl  implements LabTestService{

    private final LabTestRepository labTestRepository;
    private final LabTestDtoMapper labTestDtoMapper;

    @Autowired
    LabTestServiceImpl(LabTestRepository labTestRepository, LabTestDtoMapper labTestDtoMapper) {
        this.labTestRepository = labTestRepository;
        this.labTestDtoMapper = labTestDtoMapper;
    }

    @Override
    public Optional<LabTestDto> findLabTestById(Long labTestId) {
        return labTestRepository.findById(labTestId)
                .map(labTestDtoMapper::map);
    }

    @Override
    public Set<LabTestDto> getAllLabTest() {
        return labTestRepository.findAll()
                .stream()
                .map(labTestDtoMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public LabTestDto addLabTest(LabTestDto labTestDto) {
        LabTest labTestToSave = labTestDtoMapper.map(labTestDto);
        LabTest savedLabTest = labTestRepository.save(labTestToSave);
        return labTestDtoMapper.map(savedLabTest);
    }

    @Override
    public Optional<LabTestDto> updateLabTest(Long labTestId, LabTestDto labTestDto) {
        return labTestRepository.findById(labTestId)
                .map(target -> setEntityFields(labTestDto,target))
                .map(labTestDtoMapper::map);
    }

    @Override
    public void deleteLabTest(Long labTestDto) {
        labTestRepository.deleteById(labTestDto);
    }

    private LabTest setEntityFields(LabTestDto labTestDto, LabTest target) {
        if(labTestDto.getLabTestName() != null) {
            target.setLabTestName(labTestDto.getLabTestName());
        }
        if(labTestDto.getAlterTestName() != null) {
            target.setAlterTestName(labTestDto.getAlterTestName());
        }
        if(labTestDto.getUpperReferenceValue() != 0) {
            target.setUpperReferenceValue(labTestDto.getUpperReferenceValue());
        }
        if(labTestDto.getLowerReferenceValue() != 0) {
            target.setLowerReferenceValue(labTestDto.getLowerReferenceValue());
        }
        if(labTestDto.getResult() !=0) {
            target.setResult(labTestDto.getResult());
        }
        return target;

    }


}
