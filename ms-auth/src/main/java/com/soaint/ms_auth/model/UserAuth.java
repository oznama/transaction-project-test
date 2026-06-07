package com.soaint.ms_auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    @Id
    @Column(name = "usuario", length = 20)
    private String username;

    @Column(nullable = false)
    private String password;
}
