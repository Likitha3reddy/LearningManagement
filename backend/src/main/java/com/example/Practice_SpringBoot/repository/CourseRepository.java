package com.example.Practice_SpringBoot.repository;

import com.example.Practice_SpringBoot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByInstructorId(Long InstructorId);
    long countByInstructorId(Long instructorId);
}
