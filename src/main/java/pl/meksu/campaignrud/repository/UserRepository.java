package pl.meksu.campaignrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.meksu.campaignrud.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail(String mail);
    boolean existsByMail(String mail);
}