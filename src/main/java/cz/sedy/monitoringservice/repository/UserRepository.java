package cz.sedy.monitoringservice.repository;

import cz.sedy.monitoringservice.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByAccessToken(String accessToken);
}
