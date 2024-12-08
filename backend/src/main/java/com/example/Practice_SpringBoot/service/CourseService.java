package com.example.Practice_SpringBoot.service;

import com.example.Practice_SpringBoot.Exception.ResourceNotFoundException;
import com.example.Practice_SpringBoot.entity.Course;
import com.example.Practice_SpringBoot.entity.CourseDto;
import com.example.Practice_SpringBoot.entity.ModuleDto;
import com.example.Practice_SpringBoot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        course.setCreatedDate(LocalDateTime.now());
        return courseRepository.save(course);
    }

    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    /*
    get all courses creatted by a instrtor by id
     */
    public List<CourseDto> getCourseById(Long instructor_id){
        List<Course> courses = courseRepository.findByInstructorId(instructor_id);
        return courses.stream().map(course -> {
            CourseDto courseDTO = new CourseDto();
            courseDTO.setCourseId(course.getCourseId());
            courseDTO.setTitle(course.getTitle());
            courseDTO.setDescription(course.getDescription());
            courseDTO.setCategory(course.getCategory());
            courseDTO.setInstructorId(course.getInstructorId());
            courseDTO.setCreatedDate(course.getCreatedDate());
            courseDTO.setStatus(course.getStatus());
            courseDTO.setImageUrl(course.getImageUrl());
            return courseDTO;
        }).collect(Collectors.toList());
    }

    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        // Map Course entities to CourseDTOs
        return courses.stream().map(course -> {
            CourseDto courseDTO = new CourseDto();
            courseDTO.setCourseId(course.getCourseId());
            courseDTO.setTitle(course.getTitle());
            courseDTO.setDescription(course.getDescription());
            courseDTO.setCategory(course.getCategory());
            courseDTO.setInstructorId(course.getInstructorId());
            courseDTO.setCreatedDate(course.getCreatedDate());
            courseDTO.setStatus(course.getStatus());
            courseDTO.setImageUrl(course.getImageUrl());
            return courseDTO;
        }).collect(Collectors.toList());
    }
}
