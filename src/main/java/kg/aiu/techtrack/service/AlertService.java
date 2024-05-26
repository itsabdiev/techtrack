package kg.aiu.techtrack.service;

import kg.aiu.techtrack.entity.Alert;

import java.util.List;

public interface AlertService {
    List<Alert> getAll();

    Alert getById(Long id);

    void save(Alert alert);

    boolean existsById(Long id);

    void deleteById(Long id);
}
