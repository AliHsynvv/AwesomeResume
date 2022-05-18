package com.company;

import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class ResumeDbAppJpaSpringApplication {

    @Autowired //Loose Coupling  //=new UserDaoImpl, implements elediyi classi avtomatik inject edir
    //    @Qualifier("test") // eyer iki implements etdiyi class olarsa arasinda seçim etmək imkanını sağlıyır
    private UserServiceInter userServiceInter;

    public static void main(String[] args) {
        SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = new CommandLineRunner() {
            @Override
            public void run(String[] args) {
            }

        };
        return clr;
    }
}
