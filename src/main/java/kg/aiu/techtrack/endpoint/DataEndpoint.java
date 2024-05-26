package kg.aiu.techtrack.endpoint;

import kg.aiu.techtrack.dto.request.DataRequest;
import kg.aiu.techtrack.dto.response.DataResponse;
import kg.aiu.techtrack.dto.response.MessageResponse;
import kg.aiu.techtrack.entity.Alert;
import kg.aiu.techtrack.entity.Data;
import kg.aiu.techtrack.exception.DataForEquipmentAlreadyExistsException;
import kg.aiu.techtrack.exception.NotFoundException;
import kg.aiu.techtrack.service.DataService;
import kg.aiu.techtrack.service.EquipmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataEndpoint {

    DataService dataService;
    EquipmentService equipmentService;
    EquipmentEndpoint equipmentEndpoint;

    public List<DataResponse> getAll() {
        return dataService.getAll().stream().map(this::entityToDtoMapper).collect(Collectors.toList());
    }


    private Data dtoToEntityMapper(DataRequest request) {
        return Data.builder()
                .equipment(equipmentService.getById(request.equipmentId()))
                .pressure(request.pressure())
                .speed(request.speed())
                .registrationTime(request.registrationTime())
                .temperature(request.temperature())
                .build();
    }

    private DataResponse entityToDtoMapper(Data data) {
        return DataResponse.builder()
                .registrationTime(data.getRegistrationTime())
                .speed(data.getSpeed())
                .equipmentId(data.getEquipment().getId())
                .temperature(data.getTemperature())
                .pressure(data.getPressure())
                .build();
    }

    public DataResponse getById(Long id) {
        existsByIdOrThrowException(id);
        return entityToDtoMapper(dataService.getById(id));
    }

    public MessageResponse save(DataRequest dataRequest) {
        equipmentEndpoint.existsByIdOrThrowException(dataRequest.equipmentId());
        Optional<Data> optionalData = dataService.existsByEquipmentId(dataRequest.equipmentId());
        if (optionalData.isPresent()) throw new DataForEquipmentAlreadyExistsException();
        dataService.save(dtoToEntityMapper(dataRequest));
        return MessageResponse.builder()
                .message("Data has been created")
                .statusCode(201)
                .build();
    }

    public MessageResponse update(Long id, DataRequest dataRequest) {
        existsByIdOrThrowException(id);
        equipmentEndpoint.existsByIdOrThrowException(dataRequest.equipmentId());
        Data data = dtoToEntityMapper(dataRequest);
        data.setId(id);
        dataService.save(data);
        return MessageResponse.builder()
                .message("Data has been updated")
                .statusCode(200)
                .build();
    }

    private void existsByIdOrThrowException(Long id) {
        if (!dataService.existsById(id)) throw new NotFoundException("Data has not been found");
    }

    public MessageResponse delete(Long id) {
        existsByIdOrThrowException(id);
        dataService.deleteById(id);
        return MessageResponse.builder()
                .message("Data has been removed")
                .statusCode(200)
                .build();
    }
}
