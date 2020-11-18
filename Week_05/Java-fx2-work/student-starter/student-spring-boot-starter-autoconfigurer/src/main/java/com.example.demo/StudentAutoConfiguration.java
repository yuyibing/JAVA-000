package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {

    @Autowired
    StudentProperties studentProperties;

    @Bean
    public Student student(){
        Student student = new Student();
        student.setId(studentProperties.getId());
        student.setName(studentProperties.getName());
        return student;
    }

}
