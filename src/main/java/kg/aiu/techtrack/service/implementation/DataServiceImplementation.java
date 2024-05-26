package kg.aiu.techtrack.service.implementation;

import kg.aiu.techtrack.service.DataService;
import kg.aiu.techtrack.entity.Data;
import kg.aiu.techtrack.repository.DataRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataServiceImplementation implements DataService {

    DataRepository dataRepository;

    @Override
    public List<Data> getAll() {
        return dataRepository.findAll();
    }

    @Override
    public Data getById(Long id) {
        return dataRepository.getById(id);
    }

    @Override
    public Optional<Data> existsByEquipmentId(Long id) {
        return dataRepository.existsByEquipmentId(id);
    }

    @Override
    public void save(Data data) {
        dataRepository.save(data);
    }

    @Override
    public boolean existsById(Long id) {
        return dataRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        dataRepository.deleteById(id);
    }
}
