package kg.aiu.techtrack.service;

import kg.aiu.techtrack.entity.Equipment;

import java.util.List;

public interface EquipmentService {
    boolean existsByModel(String model);

    void save(Equipment equipmentToBeSaved);

    boolean existsById(Long id);

    void deleteById(Long id);

    Equipment getById(Long id);

    List<Equipment> getAll();
}
