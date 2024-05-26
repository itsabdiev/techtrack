package kg.aiu.techtrack.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import kg.aiu.techtrack.entity.enums.EquipmentStatus;
import kg.aiu.techtrack.entity.enums.EquipmentType;
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
@EqualsAndHashCode(of = "equipment_number", callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipment extends BaseEntity {

    UUID equipmentNumber;

    String model;

    @Enumerated(value = EnumType.STRING)
    EquipmentType type;

    @Enumerated(value = EnumType.STRING)
    EquipmentStatus status;

    Date installationDate;
}
