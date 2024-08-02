package ru.spring.weather.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
public class Event {

    public enum EventType {
        RAIN,
        SNOW,
        etc
    }

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "event_type")
    @Enumerated(EnumType.ORDINAL)
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Event(EventType eventType, User user) {
        this.eventType = eventType;
        this.user = user;
    }
}
