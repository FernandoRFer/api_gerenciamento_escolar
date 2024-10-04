package com.example.gerenciamento_escolar.controller

import com.example.gerenciamento_escolar.entities.Course
import com.example.gerenciamento_escolar.entities.Enrollment
import com.example.gerenciamento_escolar.entities.Student
import com.example.gerenciamento_escolar.repositories.EnrollmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/enrollment")
class EnrollmentController {

    @Autowired
    lateinit var repository: EnrollmentRepository

    @GetMapping
    fun index(): List<Enrollment> = repository.findAll()


    @GetMapping(value = ["/courses-enrolled/{idStudent}"])
    fun getByIdStudent(@PathVariable("idStudent") id: Int): List<Course>? {
        return repository.findByCoursesEnrollment(id)
    }

    @GetMapping(value = ["/students-enrolled/{idCourse}"])
    fun getByIdCourse(@PathVariable("idCourse") id: Int): List<Student>? {
        return repository.findByStudentsEnrollment(id)
    }

    @PostMapping
    fun create(
        @RequestBody enrollment: Enrollment,
    ): ResponseEntity<Enrollment> {
        val created = repository.save(enrollment)
        return ResponseEntity(created, HttpStatus.CREATED)
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Any> {

        var result = repository.deleteById(id)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @DeleteMapping
    fun deleteById(@RequestParam params: Map<String, String>): ResponseEntity<Any> {
        var idStudent = params.get("idstudent").toString().toInt()
        var idCourse = params.get("idcourse").toString().toInt()

        var idEnrollment = repository.findByIdStudentAndIdCourse(idStudent = idStudent, idCourse = idCourse)

        repository.deleteById(idEnrollment)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}