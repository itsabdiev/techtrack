package kg.aiu.techtrack.dto.response;

import lombok.Builder;
import java.sql.Timestamp;

@Builder
public record DataResponse(
        Integer temperature,
        Integer speed,
        Integer pressure,
        Timestamp registrationTime,
        Long equipmentId
) {
}
