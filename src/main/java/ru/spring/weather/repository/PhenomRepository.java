package ru.spring.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.weather.model.Phenom;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhenomRepository extends JpaRepository<Phenom, UUID> {
    List<Phenom> getPhenomsByUserId(UUID userId);
}
