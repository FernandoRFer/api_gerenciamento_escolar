package com.example.gerenciamento_escolar.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "Course")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Size(max = 50)
    var description: String,

    var syllabus: String,

)

