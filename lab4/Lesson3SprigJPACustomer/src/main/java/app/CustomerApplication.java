package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class CustomerApplication implements CommandLineRunner{
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	PublisherRepository publisherRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Department dep = new Department("Computer Science");
		Employee emp1 = new Employee("1234567890", "Alex", dep);
		Employee emp2 = new Employee("987654321", "Bob", dep);

		dep.setEmployee(emp1);
		dep.setEmployee(emp2);


		departmentRepository.save(dep);

		departmentRepository.findAll().forEach(System.out::println);
		*/


		/*
		//	Book and Publisher
		Publisher p1 = new Publisher("Kiiv entertainment");
		publisherRepository.save(p1);
		Publisher p2 = new Publisher("Moscow entertainment");
		publisherRepository.save(p2);

		Book b1 = new Book("Harry Potter", "J.k", "1234567");
		b1.setPublisher(p1);
		Book b2 = new Book("Harry Not Potter", "J.k", "098765432");
		b2.setPublisher(p2);
		Book b3 = new Book("Ron Not Potter", "J.k", "2222222");
		b3.setPublisher(p2);

		bookRepository.save(b1);
		bookRepository.save(b2);
		bookRepository.save(b3);
		*/

		/*
		// Flight and passenger
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");

		LocalDate date1 = LocalDate.parse("12-12-12", formatter);
		LocalDate date2 = LocalDate.parse("25-10-23", formatter);
		LocalDate date3 = LocalDate.parse("01-01-30", formatter);

		Flight f1 = new Flight("123", "Taraz", "USA", date1);
		Flight f2 = new Flight("456", "Berlin", "Canada", date2);
		Flight f3 = new Flight("789", "Tokyo", "Brazil", date3);
		Passenger passenger = new Passenger("Mike", f1);
		passenger.setFlight(f2);
		passenger.setFlight(f3);

		passengerRepository.save(passenger);
		passengerRepository.findAll().forEach(System.out::println);
		*/

		// School and Student
		Student s1 = new Student(1111L, "John", "Doe");
		Student s2 = new Student(2222L, "Jane", "Doe");
		Student s3 = new Student(3333L, "Jack", "Doe");

		School school = new School("Gymnasium", s1);
		school.setStudent(s2);
		school.setStudent(s3);

		schoolRepository.save(school);
	}
}
