package com.SpringDataHibernate.JPA.demo.course.jdbc;

import com.SpringDataHibernate.JPA.demo.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CourseCommandLineRunner implements CommandLineRunner {


//    @Autowired
//    private CourseJdbcRepository repository;

//    @Autowired
//    private CourseJpaRepository repository;


    @Autowired
    private CourseSpringJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1, "This is jay 123", "none"));
        repository.deleteById(1l);

        repository.save(new Course(2, "This is jay 456", "none"));
        repository.save(new Course(3, "This is jay 789", "none"));
        repository.save(new Course(4,"This is jay 101112", "none"));

        System.out.println(repository.findById(2l));
        System.out.println(repository.findById(3l));

        System.out.println(repository.findCourseByAuthor("none"));
    }
}
