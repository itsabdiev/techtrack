package kg.aiu.techtrack.endpoint;


import kg.aiu.techtrack.dto.request.EquipmentRequest;
import kg.aiu.techtrack.dto.response.EquipmentResponse;
import kg.aiu.techtrack.dto.response.MessageResponse;
import kg.aiu.techtrack.entity.Equipment;
import kg.aiu.techtrack.exception.EquipmentAlreadyExistException;
import kg.aiu.techtrack.exception.NotFoundException;
import kg.aiu.techtrack.service.EquipmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EquipmentEndpoint {

    EquipmentService equipmentService;

    public MessageResponse save(EquipmentRequest equipmentRequest) {
        if (equipmentService.existsByModel(equipmentRequest.model())) throw new EquipmentAlreadyExistException();
        equipmentService.save(dtoToEntityMapper(equipmentRequest));
        return MessageResponse.builder()
                .message("Equipment has been created")
                .statusCode(201)
                .build();
    }

    public MessageResponse update(Long id, EquipmentRequest equipmentRequest) {
        existsByIdOrThrowException(id);
        Equipment equipment = dtoToEntityMapper(equipmentRequest);
        equipment.setId(id);
        equipmentService.save(equipment);
        return MessageResponse.builder()
                .message("Equipment has been updated")
                .statusCode(200)
                .build();

    }

    public MessageResponse delete(Long id) {
        existsByIdOrThrowException(id);
        equipmentService.deleteById(id);
        return MessageResponse.builder()
                .message("Equipment has been removed")
                .statusCode(200)
                .build();

    }

    public EquipmentResponse getById(Long id) {
        existsByIdOrThrowException(id);
        return entityToDtoMapper(equipmentService.getById(id));
    }

    private void existsByIdOrThrowException(Long id) {
        if (!equipmentService.existsById(id)) throw new NotFoundException("Equipment has not been found");
    }

    public List<EquipmentResponse> getAll() {
        return equipmentService.getAll().stream().map(this::entityToDtoMapper).collect(Collectors.toList());
    }

    private Equipment dtoToEntityMapper(EquipmentRequest equipmentRequest) {
        return Equipment.builder()
                .equipmentNumber(UUID.randomUUID())
                .model(equipmentRequest.model())
                .type(equipmentRequest.type())
                .installationDate(equipmentRequest.installationDate())
                .status(equipmentRequest.status())
                .build();
    }


    private EquipmentResponse entityToDtoMapper(Equipment equipment) {
        return EquipmentResponse.builder()
                .id(equipment.getId())
                .type(equipment.getType())
                .status(equipment.getStatus())
                .installationDate(equipment.getInstallationDate())
                .equipmentNumber(equipment.getEquipmentNumber())
                .model(equipment.getModel())
                .build();
    }



}
