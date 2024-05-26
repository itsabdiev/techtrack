package kg.aiu.techtrack.web;


import kg.aiu.techtrack.dto.request.EquipmentRequest;
import kg.aiu.techtrack.dto.response.EquipmentResponse;
import kg.aiu.techtrack.dto.response.MessageResponse;
import kg.aiu.techtrack.endpoint.EquipmentEndpoint;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EquipmentController {

    EquipmentEndpoint equipmentEndpoint;

    @GetMapping
    public List<EquipmentResponse> getAll() {
        return equipmentEndpoint.getAll();
    }

    @GetMapping("/{id}")
    public EquipmentResponse getById(@PathVariable Long id) {
        return equipmentEndpoint.getById(id);
    }

    @PostMapping
    public MessageResponse save(@RequestBody EquipmentRequest equipmentRequest) {
        return equipmentEndpoint.save(equipmentRequest);
    }

    @PutMapping("/{id}")
    public MessageResponse update(@RequestBody EquipmentRequest equipmentRequest, @PathVariable Long id) {
        return equipmentEndpoint.update(id,equipmentRequest);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return equipmentEndpoint.delete(id);
    }

}
