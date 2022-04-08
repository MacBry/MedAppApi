package pl.mac.bry.lab_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabTestFacade {

    private final LabTestRepository repository;

    @Autowired
    public LabTestFacade(LabTestRepository repository) {
        this.repository = repository;
    }

    public Optional<LabTest> getLabTestById(Long labTestId){
        return repository.findById(labTestId);
    }
}
