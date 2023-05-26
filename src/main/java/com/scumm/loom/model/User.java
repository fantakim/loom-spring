package com.scumm.loom.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    public User(String email, String username) {
        this.email = email;
        this.username = username;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String email, String username) {
        this.email = email;
        this.username = username;
        this.updatedAt = LocalDateTime.now();
    }
}
