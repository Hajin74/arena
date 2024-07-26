package org.example.boxingarena.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String gym;

    public Player(String name, String email, String password, String gym) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gym = gym;
    }

    public Player(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
