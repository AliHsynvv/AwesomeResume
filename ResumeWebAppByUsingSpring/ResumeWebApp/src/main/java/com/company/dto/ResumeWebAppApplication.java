package com.company.dto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.company.controller","com.company"})
public class ResumeWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeWebAppApplication.class, args);
    }
//
//    @Autowired
//    private UserServiceInter userServiceInter;
//
//    @Bean
//    public CommandLineRunner run() {
//        CommandLineRunner clr = new CommandLineRunner() {
//            @Override
//            public void run(String[] args) {
//                User u = userServiceInter.getByteID(5);
//                System.out.println(u.getName());
//            }
//
//        };
//        return clr;
//    }
}
