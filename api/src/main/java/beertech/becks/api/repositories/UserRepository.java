package beertech.becks.api.repositories;

import beertech.becks.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	Boolean existsByDocumentNumber(String documentNumber);

}
