package kg.aiu.techtrack.service.implementation;

import kg.aiu.techtrack.entity.Alert;
import kg.aiu.techtrack.entity.Equipment;
import kg.aiu.techtrack.entity.enums.EquipmentStatus;
import kg.aiu.techtrack.entity.enums.EquipmentType;
import kg.aiu.techtrack.repository.AlertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AlertServiceImplementationTest {

    @Mock
    AlertRepository alertRepository;

    @InjectMocks
    AlertServiceImplementation alertServiceImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        Equipment equipment = Equipment.builder()
                .id(1L)
                .status(EquipmentStatus.IN_USAGE)
                .equipmentNumber(UUID.randomUUID())
                .model("Model 1")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .hasBeenRemoved(false)
                .installationDate(new Date())
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .type(EquipmentType.HVAC)
                .build();
        Alert alert1 = Alert.builder()
                .id(1L)
                .equipment(equipment)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .hasBeenRemoved(false)
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .registrationTime(Timestamp.valueOf(LocalDateTime.now()))
                .warningMessage("WARN 1")
                .build();
        Alert alert2 = Alert.builder()
                .id(2L)
                .equipment(equipment)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .hasBeenRemoved(false)
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .registrationTime(Timestamp.valueOf(LocalDateTime.now()))
                .warningMessage("WARN 2")
                .build();

        List<Alert> actualList = List.of(alert1,alert2);

        when(alertRepository.findAll()).thenReturn(actualList);

        List<Alert> list = alertServiceImplementation.getAll();

        verify(alertRepository,times(1)).findAll();
        assertIterableEquals(list,actualList);
        assertEquals(2,list.size());
    }

    @Test
    void getById() {
        Equipment equipment = Equipment.builder()
                .id(1L)
                .status(EquipmentStatus.IN_USAGE)
                .equipmentNumber(UUID.randomUUID())
                .model("Model 1")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .hasBeenRemoved(false)
                .installationDate(new Date())
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .type(EquipmentType.HVAC)
                .build();

        Alert alert = Alert.builder()
                .id(1L)
                .equipment(equipment)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .hasBeenRemoved(false)
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .registrationTime(Timestamp.valueOf(LocalDateTime.now()))
                .warningMessage("WARN 1")
                .build();

        when(alertRepository.getById(anyLong())).thenReturn(alert);

        Alert alert1 = alertServiceImplementation.getById(1L);

        verify(alertRepository,times(1)).getById(anyLong());
        assertEquals(alert1,alert);
    }

    @Test
    void save() {
        Equipment equipment = Equipment.builder()
                .id(1L)
                .status(EquipmentStatus.IN_USAGE)
                .equipmentNumber(UUID.randomUUID())
                .model("Model 1")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .hasBeenRemoved(false)
                .installationDate(new Date())
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .type(EquipmentType.HVAC)
                .build();

        Alert alert = Alert.builder()
                .id(1L)
                .equipment(equipment)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .hasBeenRemoved(false)
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .registrationTime(Timestamp.valueOf(LocalDateTime.now()))
                .warningMessage("WARN 1")
                .build();

        when(alertRepository.save(any())).thenReturn(alert);
        alertServiceImplementation.save(alert);
        verify(alertRepository,times(1)).save(any());
    }

    @Test
    void existsById() {
        when(alertRepository.existsById(anyLong())).thenReturn(true);
        boolean b = alertServiceImplementation.existsById(1L);
        verify(alertRepository,times(1)).existsById(1L);
        assertTrue(b);
    }

    @Test
    void deleteById() {
        alertServiceImplementation.deleteById(1L);
        verify(alertRepository,times(1)).removeById(1L);
    }
}