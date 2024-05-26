package kg.aiu.techtrack.web;


import kg.aiu.techtrack.dto.request.AlertRequest;
import kg.aiu.techtrack.dto.request.EquipmentRequest;
import kg.aiu.techtrack.dto.response.AlertResponse;
import kg.aiu.techtrack.dto.response.EquipmentResponse;
import kg.aiu.techtrack.dto.response.MessageResponse;
import kg.aiu.techtrack.endpoint.AlertEndpoint;
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
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AlertController {

    AlertEndpoint alertEndpoint;

    @GetMapping
    public List<AlertResponse> getAll() {
        return alertEndpoint.getAll();
    }

    @GetMapping("/{id}")
    public AlertResponse getById(@PathVariable Long id) {
        return alertEndpoint.getById(id);
    }

    @PostMapping
    public MessageResponse save(@RequestBody AlertRequest alertRequest) {
        return alertEndpoint.save(alertRequest);
    }

    @PutMapping("/{id}")
    public MessageResponse update(@RequestBody AlertRequest alertRequest, @PathVariable Long id) {
        return alertEndpoint.update(id,alertRequest);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return alertEndpoint.delete(id);
    }

}
