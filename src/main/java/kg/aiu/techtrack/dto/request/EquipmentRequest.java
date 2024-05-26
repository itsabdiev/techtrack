package kg.aiu.techtrack.dto.request;

import kg.aiu.techtrack.entity.enums.EquipmentStatus;
import kg.aiu.techtrack.entity.enums.EquipmentType;
import lombok.Builder;

import java.util.Date;

@Builder
public record EquipmentRequest(
        String model,
        EquipmentType type,
        EquipmentStatus status,
        Date installationDate
) {
}
