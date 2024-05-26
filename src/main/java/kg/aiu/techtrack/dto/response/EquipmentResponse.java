package kg.aiu.techtrack.dto.response;

import kg.aiu.techtrack.entity.enums.EquipmentStatus;
import kg.aiu.techtrack.entity.enums.EquipmentType;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record EquipmentResponse(
        Long id,
        UUID equipmentNumber,
        String model,
        EquipmentType type,
        EquipmentStatus status,
        Date installationDate
) {
}
