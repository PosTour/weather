package ru.spring.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.weather.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
