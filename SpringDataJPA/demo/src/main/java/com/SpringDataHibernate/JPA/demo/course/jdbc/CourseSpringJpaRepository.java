package com.SpringDataHibernate.JPA.demo.course.jdbc;

import com.SpringDataHibernate.JPA.demo.course.Course;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CourseSpringJpaRepository extends JpaRepository<Course, Long> {
        List<Course> findCourseByAuthor(String author);


}
