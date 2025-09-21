package com.PayMate.user_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Table(name = "pay_mate_user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must be not blank")
    @Size(min = 3 , max = 50, message = "Name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    private String name;


    @Column(unique = true)
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    @Size(max = 100 , message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Password must be not blank")
    @Size(min = 8 , max = 100 , message = "Password must be at least   8 character long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character"
    )
    private String password;


    @Column(name = "created_at", nullable = false , updatable = false)
    private Instant createdAt;

    @PrePersist
    public void PrePersist(){
        if (this.createdAt==null){
            this.createdAt = Instant.now();
        }
    }

}
