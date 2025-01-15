package com.example.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return  args -> {
			Address address = new Address(
					"Kurt-Schumacher",
					22,
					67663,
					"Kaiserslautern",
					"Germany"
			);
			String email = "willywakam@gmail.com";
			Student student = new Student(
					"Willy",
					"Wakam",
					email,
					Gender.MALE,
					address,
					List.of("Physics", "Computer Science"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			// usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);

			repository.findStudentByEmail(email)
					.ifPresentOrElse(s -> { System.out.println(s + " already exists");},
							() -> {						repository.insert(student);	});
		};
	}

	private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.isEmpty()) repository.insert(student);
		else throw new IllegalStateException(student + " already exists");
		if (students.size() > 1) throw new IllegalStateException("found Many students with email "+ email);
	}

}
