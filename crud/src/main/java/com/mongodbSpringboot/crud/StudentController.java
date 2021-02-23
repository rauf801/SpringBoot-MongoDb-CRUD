package com.mongodbSpringboot.crud;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodbSpringboot.crud.Repository.StudentRepository;
import com.mongodbSpringboot.crud.model.Student;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StudentController {

	 @Autowired
	 StudentRepository repository;
	 
	 @GetMapping("/")
	 public String welcome() {
		 return "hello mahn";
	 }
	 
	 @GetMapping("/students")
	  public List<Student> getAllStudents() {
		return repository.findAll() ;
	    
	  }
	 @PostMapping("/studentsave")
	 public String createDept(@RequestBody Student student) {
	 	repository.save(student);
	 	return "saved with id=" +student.getId();
	 }
	 
	 @GetMapping("/student/{id}")
	 public ResponseEntity<?> getStudent(@PathVariable ("id")int id) {

			try {
				Optional<Student> student = repository.findById(id);

				if (student != null) {
					return ResponseEntity.status(HttpStatus.OK).body(student);

				} else {
					repository.deleteById(id);
					Map<String, String> errors = new LinkedHashMap<>();
					errors.put("status", "404");
					errors.put("error", "Not Found");
					errors.put("message", "No Student Available in this ID: " + id);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);

				}

			} catch (Exception e) {

			repository.deleteById(id);
				Map<String, String> errors = new LinkedHashMap<>();
				errors.put("status", "404");
				errors.put("error", "Not Found");
				errors.put("message", "No Student Available in this ID: " + id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);

			}
		}
	 @DeleteMapping("/delete/{id}")
	 public String deleteStudent(@PathVariable int id) {
		 repository.deleteById(id);
		return "Student delete with id:" +id;
		 
	 }
}