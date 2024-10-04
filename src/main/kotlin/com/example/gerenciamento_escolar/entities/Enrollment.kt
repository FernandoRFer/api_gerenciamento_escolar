package com.example.gerenciamento_escolar.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*


@Entity
@Table(name="enrollment")
data class Enrollment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,


    @ManyToOne
    @JoinColumn(
        name = "id_student",referencedColumnName = "id",)
    var student: Student?,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course", referencedColumnName = "id",)
    var course: Course?,


    )

//@Entity
//@Table(name="enrollment")
//class Enrollment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//     val id: Int
//
//    @ManyToOne
//    @JoinColumn(name = "id_student", referencedColumnName = "id")
//    @JsonIgnore
//     var student: Student
//
//    @ManyToOne
//    @JoinColumn(name = "id_course", referencedColumnName = "id")
//    @JsonIgnore
//     var course: Course
//
//    constructor(id: Int, student: Student, course: Course) {
//        this.id = id
//        this.student = student
//        this.course = course
//    }
//}

