package kg.aiu.techtrack.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@Entity
@SuperBuilder
@Table(name = "equipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "equipment_id", callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipment extends BaseEntity {

    UUID equipmentId;
    String model;
    String type;
    String status;
    Date installationDate;
}
