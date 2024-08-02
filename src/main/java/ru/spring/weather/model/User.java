package ru.spring.weather.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @UuidGenerator
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "chat_id")
    private long chatId;

    @OneToMany(mappedBy = "user")
    private List<Event> events;

    public User(long chatId) {
        this.chatId = chatId;
    }
}
