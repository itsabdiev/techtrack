package kg.aiu.techtrack.service.implementation;


import kg.aiu.techtrack.entity.Alert;
import kg.aiu.techtrack.repository.AlertRepository;
import kg.aiu.techtrack.service.AlertService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlertServiceImplementation implements AlertService {

    AlertRepository alertRepository;

    @Override
    public List<Alert> getAll() {
        return alertRepository.findAll();
    }

    @Override
    public Alert getById(Long id) {
        return alertRepository.getById(id);
    }

    @Override
    public void save(Alert alert) {
        alertRepository.save(alert);
    }

    @Override
    public boolean existsById(Long id) {
        return alertRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        alertRepository.removeById(id);
    }
}
