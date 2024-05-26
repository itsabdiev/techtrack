package kg.aiu.techtrack.dto.request;

import lombok.Builder;
import java.sql.Timestamp;

@Builder
public record DataRequest(
        Integer temperature,
        Integer speed,
        Integer pressure,
        Timestamp registrationTime,
        Long equipmentId
) {
}
