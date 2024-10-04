package com.example.gerenciamento_escolar.entities


import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.annotation.Nonnull
import jakarta.persistence.*
import jakarta.validation.constraints.Size


@Entity
@Table(name="student")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Nonnull
    @Size(min = 1, max = 50)
    var name: String,
)