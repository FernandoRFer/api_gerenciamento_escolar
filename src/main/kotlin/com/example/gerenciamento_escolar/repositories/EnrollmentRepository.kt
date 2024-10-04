package com.example.gerenciamento_escolar.repositories

import com.example.gerenciamento_escolar.entities.Course
import com.example.gerenciamento_escolar.entities.Enrollment
import com.example.gerenciamento_escolar.entities.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface EnrollmentRepository : JpaRepository<Enrollment, Int> {

    @Query("select x.course from Enrollment  x where x.student.id = ?1")
    fun findByCoursesEnrollment(idStudent: Int): List<Course>

    @Query("select x.student from Enrollment x where x.course.id = ?1")
    fun findByStudentsEnrollment(idCourse: Int): List<Student>

//    @Query(value = "DELETE FROM enrollment e WHERE e.id_student = :id_student AND e.id_course = :id_course", nativeQuery = true)
//    fun deleteByIdStudentAndIdCourse(@Param("id_student")idStudent: Int,@Param("id_course") idCourse: Int): Void

    @Query(value = "SELECT e.id FROM enrollment e WHERE e.id_student = :id_student AND e.id_course = :id_course", nativeQuery = true)
    fun findByIdStudentAndIdCourse(@Param("id_student")idStudent: Int,@Param("id_course") idCourse: Int): Int

}
