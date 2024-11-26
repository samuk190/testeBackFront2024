package com.teste.entrevista.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teste.entrevista.staff.model.User;
import java.util.Optional;
import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
        Optional<User> findByUsername(String username);

}
