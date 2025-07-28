package app;

import domain.Course;
import domain.Department;
import domain.Grade;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.CourseRepository;
import repositories.DepartmentRepository;
import repositories.StudentRepository;

import java.time.LocalDate;


@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner{
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	DepartmentRepository departmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student student1 = new Student("1234", "Mike");
		Department department1 = new Department("Department 1");

		student1.setDepartment(department1);

		Grade grade1 = new Grade("Grade 1");
		Grade grade2 = new Grade("Grade 2");

		student1.setGrade(grade1);
		student1.setGrade(grade2);


		Course course1 = new Course("Course 1");
		courseRepository.save(course1);

		grade1.setCourse(course1);
		grade2.setCourse(course1);

		studentRepository.save(student1);
	}
}
