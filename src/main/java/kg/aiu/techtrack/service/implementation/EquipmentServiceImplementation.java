package kg.aiu.techtrack.service.implementation;

import kg.aiu.techtrack.entity.Equipment;
import kg.aiu.techtrack.repository.EquipmentRepository;
import kg.aiu.techtrack.service.EquipmentService;
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
public class EquipmentServiceImplementation implements EquipmentService {

    EquipmentRepository equipmentRepository;

    @Override
    public boolean existsByModel(String model) {
        return equipmentRepository.existsByModel(model);
    }

    @Override
    public void save(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public boolean existsById(Long id) {
        return equipmentRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        equipmentRepository.removeById(id);
    }

    @Override
    public Equipment getById(Long id) {
        return equipmentRepository.getById(id);
    }

    @Override
    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }
}
