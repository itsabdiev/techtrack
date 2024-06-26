package kg.aiu.techtrack.service;

import kg.aiu.techtrack.entity.Data;

import java.util.List;
import java.util.Optional;

public interface DataService {
    List<Data> getAll();

    Data getById(Long id);

    Optional<Data> existsByEquipmentId(Long id);

    void save(Data data);

    boolean existsById(Long id);

    void deleteById(Long id);
}
