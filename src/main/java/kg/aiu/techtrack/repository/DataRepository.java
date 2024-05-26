package kg.aiu.techtrack.repository;

import kg.aiu.techtrack.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DataRepository extends JpaRepository<Data,Long> {

    @Override
    @Query(value = "SELECT * FROM datas WHERE has_been_removed = false",
            nativeQuery = true )
    List<Data> findAll();

    @Override
    @Query(value = "SELECT d.*  FROM datas d WHERE d.id = ? AND d.has_been_removed = false",
            nativeQuery = true )
    Data getById(Long id);


    @Query(value = "SELECT d.*  FROM datas d WHERE d.equipment_id = ? AND d.has_been_removed = false",
            nativeQuery = true )
    Optional<Data> existsByEquipmentId(Long id);

    @Query(value = "SELECT COUNT(d) > 0 FROM datas d WHERE d.id = ? AND d.has_been_removed = false",
            nativeQuery = true )
    boolean existsById(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE datas SET has_been_removed = true WHERE id = ?",
            nativeQuery = true)
    void removeById(Long id);
}
