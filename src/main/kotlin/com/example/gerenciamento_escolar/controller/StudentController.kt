package com.example.gerenciamento_escolar.controller

import com.example.gerenciamento_escolar.entities.Course
import com.example.gerenciamento_escolar.entities.Student
import com.example.gerenciamento_escolar.repositories.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController {

    @Autowired
    lateinit var repository: StudentRepository

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<Student>> = ResponseEntity(repository.findAll(),HttpStatus.OK)


    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<Student> {
        val result = repository.findById(id).orElse(null)

        return if (result != null) {
            ResponseEntity(result, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun create(
        @RequestBody student: Student,
    ): ResponseEntity<Student> {
        val created = repository.save(student)
        return ResponseEntity(created, HttpStatus.CREATED)
    }

    @PutMapping
    fun updateById( @RequestBody student: Student): ResponseEntity<Student> {
        if (!repository.existsById(student.id)) return ResponseEntity(HttpStatus.NOT_FOUND)

        repository.save(student)

        return ResponseEntity(student, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Int): ResponseEntity<Any> {

        if (!repository.existsById(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        repository.deleteById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}