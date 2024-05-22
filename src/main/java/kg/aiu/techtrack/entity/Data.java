package kg.aiu.techtrack.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@SuperBuilder
@Table(name = "datas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Data extends BaseEntity {

    String temperature;
    Integer speed;
    Integer pressure;
    Timestamp registrationTime;

    @ManyToOne
    Equipment equipment;
}
