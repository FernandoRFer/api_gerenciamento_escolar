package com.example.gerenciamento_escolar.controller

import com.example.gerenciamento_escolar.entities.Course
import com.example.gerenciamento_escolar.repositories.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/course")
class CourseController {

    @Autowired
    lateinit var repository: CourseRepository

    @GetMapping
    fun getAllUsers(): List<Course> = repository.findAll()


    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ResponseEntity<Course> {
        val result = repository.findById(id).orElse(null)

        return if (result != null) {
            ResponseEntity(result, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun create(
        @RequestBody course: Course,
    ): Course {
        return repository.save(course)
    }

    @PutMapping
    fun updateById( @RequestBody course: Course): ResponseEntity<Course> {
        if (!repository.existsById(course.id)) return ResponseEntity(HttpStatus.NOT_FOUND)

        repository.save(course)

        return ResponseEntity(course, HttpStatus.OK)
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