package kg.aiu.techtrack.repository;

import kg.aiu.techtrack.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert,Long> {

    @Override
    @Query(value = "SELECT * FROM alerts WHERE has_been_removed = false",
            nativeQuery = true )
    List<Alert> findAll();

    @Override
    @Query(value = "SELECT a.*  FROM alerts a WHERE a.id = ? AND a.has_been_removed = false",
            nativeQuery = true )
    Alert getById(Long id);

    @Query(value = "SELECT COUNT(a) > 0 FROM alerts a WHERE a.id = ? AND a.has_been_removed = false",
            nativeQuery = true )
    boolean existsById(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE alerts SET has_been_removed = true WHERE id = ?",
            nativeQuery = true)
    void removeById(Long id);
}
