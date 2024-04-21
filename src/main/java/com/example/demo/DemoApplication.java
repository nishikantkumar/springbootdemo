package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.List;

@SpringBootApplication
public class DemoApplication  /* extends SpringBootServletInitializer */implements CommandLineRunner{

    /**
     * SpringBootServletInitializer is needed if u generate war file and deploy inside webserver
     */

    @Autowired
    PersonRepo repo;

	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Person p1 = new Person();
        p1.setFirstname("abc1");
        p1.setLastname("xyz1");
        repo.save(p1);

        Person p2 = new Person();
        p2.setFirstname("abc2");
        p2.setLastname("xyz2");
        repo.save(p2);

        Person p3 = new Person();
        p3.setFirstname("abc3");
        p3.setLastname("xyz3");
        repo.save(p3);
        List<Person> li = repo.findAll();
        li.stream().forEach(personelement-> System.out.println(personelement));
    }
}
