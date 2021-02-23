package com.mongodbSpringboot.crud.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodbSpringboot.crud.model.Student;




public interface StudentRepository extends MongoRepository<Student, Integer> {

	}
