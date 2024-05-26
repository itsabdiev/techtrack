package kg.aiu.techtrack.service.implementation;

import kg.aiu.techtrack.repository.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class EquipmentServiceImplementationTest {

    @Mock
    EquipmentRepository equipmentRepository;

    @InjectMocks
    EquipmentServiceImplementation equipmentServiceImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsByModel() {
        Mockito.when(equipmentRepository.existsByModel(Mockito.anyString())).thenReturn(true);
        boolean b = equipmentServiceImplementation.existsByModel("Model 1");
        assertTrue(b);
        verify(equipmentRepository,times(1)).existsByModel(anyString());
    }

    @Test
    void save() {
    }

    @Test
    void existsById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }
}