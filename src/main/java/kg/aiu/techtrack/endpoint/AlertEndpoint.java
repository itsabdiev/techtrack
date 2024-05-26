package kg.aiu.techtrack.endpoint;


import kg.aiu.techtrack.dto.request.AlertRequest;
import kg.aiu.techtrack.dto.response.AlertResponse;
import kg.aiu.techtrack.dto.response.MessageResponse;
import kg.aiu.techtrack.entity.Alert;
import kg.aiu.techtrack.entity.Equipment;
import kg.aiu.techtrack.exception.NotFoundException;
import kg.aiu.techtrack.service.AlertService;
import kg.aiu.techtrack.service.EquipmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlertEndpoint {

    AlertService alertService;
    EquipmentEndpoint equipmentEndpoint;
    EquipmentService equipmentService;

    public List<AlertResponse> getAll() {
        return alertService.getAll().stream().map(this::entityToDtoMapper).collect(Collectors.toList());
    }

    public AlertResponse getById(Long id) {
        existsByIdOrThrowException(id);
        return entityToDtoMapper(alertService.getById(id));
    }

    public MessageResponse save(AlertRequest alertRequest) {
        equipmentEndpoint.existsByIdOrThrowException(alertRequest.equipmentId());
        alertService.save(dtoToEntityMapper(alertRequest));
        return MessageResponse.builder()
                .message("Alert has been created")
                .statusCode(201)
                .build();
    }

    private AlertResponse entityToDtoMapper(Alert alert) {
        return AlertResponse.builder()
                .registrationTime(alert.getRegistrationTime())
                .warningMessage(alert.getWarningMessage())
                .equipmentId(alert.getEquipment().getId())
                .build();
    }
    
    private Alert dtoToEntityMapper(AlertRequest request) {
        return Alert.builder()
                .registrationTime(request.registrationTime())
                .warningMessage(request.warningMessage())
                .equipment(equipmentService.getById(request.equipmentId()))
                .build();
    }

    public MessageResponse update(Long id, AlertRequest alertRequest) {
        existsByIdOrThrowException(id);
        equipmentEndpoint.existsByIdOrThrowException(alertRequest.equipmentId());
        Alert alert = dtoToEntityMapper(alertRequest);
        alert.setId(id);
        alertService.save(alert);
        return MessageResponse.builder()
                .message("Alert has been updated")
                .statusCode(200)
                .build();
    }

    private void existsByIdOrThrowException(Long id) {
        if (!alertService.existsById(id)) throw new NotFoundException("Alert has not been found");
    }

    public MessageResponse delete(Long id) {
        existsByIdOrThrowException(id);
        alertService.deleteById(id);
        return MessageResponse.builder()
                .message("Alert has been removed")
                .statusCode(200)
                .build();
    }
}
