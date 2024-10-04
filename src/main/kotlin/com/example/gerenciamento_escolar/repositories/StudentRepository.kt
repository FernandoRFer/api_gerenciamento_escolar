package com.example.gerenciamento_escolar.repositories

import com.example.gerenciamento_escolar.entities.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Int> {}
