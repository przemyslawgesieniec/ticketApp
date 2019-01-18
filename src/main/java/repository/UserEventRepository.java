package main.java.repository;

import main.java.entity.UserEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEventRepository extends JpaRepository<UserEventEntity, Long> {

    List<UserEventEntity> getAllByUserIdAndState(Long id, int state);
    Optional<UserEventEntity> findOneByUserIdAndEventId(Long userId, Long eventId);

}
