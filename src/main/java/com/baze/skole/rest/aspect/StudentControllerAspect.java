package com.baze.skole.rest.aspect;

import com.baze.skole.dto.studenti.StudentDTO;
import com.baze.skole.model.studenti.Student;
import lombok.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentControllerAspect {
    @Before(value = "execution(* com.baze.skole.rest.controller.StudentController.*(..)) && args(id)")
    public void beforeAdvice(JoinPoint joinPoint, Long id) {
        System.out.println("Accessing student with id " + id);
        System.out.println("Method name: " + joinPoint.getSignature());
    }

    @AfterReturning(value = "execution(* com.baze.skole.rest.controller.StudentController.*(..))", returning = "student")
    public void afterReturningAdvice(JoinPoint joinPoint, ResponseEntity<StudentDTO> student) {
        System.out.println(student.getBody());
    }

}
