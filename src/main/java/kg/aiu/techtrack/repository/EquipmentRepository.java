package kg.aiu.techtrack.repository;

import kg.aiu.techtrack.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment,Long> {

    @Query(value = "SELECT COUNT(e) > 0 FROM equipments e WHERE e.model = ? AND e.has_been_removed = false",
            nativeQuery = true )
    boolean existsByModel(String model);

    @Modifying
    @Transactional
    @Query(value = "UPDATE equipments SET has_been_removed = true WHERE id = ?",
            nativeQuery = true)
    void removeById(Long id);


    @Query(value = "SELECT COUNT(e) > 0 FROM equipments e WHERE e.id = ? AND e.has_been_removed = false",
            nativeQuery = true )
    boolean existsById(Long id);

    @Override
    @Query(value = "SELECT e.*  FROM equipments e WHERE e.id = ? AND e.has_been_removed = false",
            nativeQuery = true )
    Equipment getById(Long id);

    @Override
    @Query(value = "SELECT * FROM equipments WHERE has_been_removed = false",
            nativeQuery = true )
    List<Equipment> findAll();
}
