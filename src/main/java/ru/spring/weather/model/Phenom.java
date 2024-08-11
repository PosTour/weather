package ru.spring.weather.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
public class Phenom {

    public enum Type {
        CLEAR("Clear"),
        HAIL("Hail"),
        OVERCAST("Overcast"),
        RAIN("Rain"),
        SHOWERS("Showers"),
        SLEET("Sleet"),
        SNOW("Snow"),
        THUNDERSTORM("Thunderstorm");

        private final String label;

        Type(String label) {
            this.label = label;
        }

        private static final Map<String, Type> LOOKUP_MAP = new HashMap<>();

        static {
            for (Type type : values()) {
                LOOKUP_MAP.put(type.label, type);
            }
        }

        public static Type getTypeByString(String type) {
            return LOOKUP_MAP.get(type);
        }

        @Override
        public String toString() {
            return label;
        }
    }

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "city")
    private String city;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Phenom(Type type, User user) {
        this.type = type;
        this.user = user;
    }
}
