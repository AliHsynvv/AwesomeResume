package com.company.controller;

import com.company.DTO.ResponseDTO;
import com.company.DTO.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {
    @Autowired()
    private UserServiceInter userService;

    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age
    ) {
        List<User> users = userService.getAll(name, surname, age);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }
        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @GetMapping("/foo")
    public ResponseEntity<ResponseDTO> foo(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age
    ) {
        List<User> users = userService.getAll(name, surname, age);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }
        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUsers(@PathVariable("id") int id) {
        User user = userService.getByteID(id);
        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUsers(@PathVariable("id") int id) {
        User user = userService.getByteID(id);
        userService.removeUser(id);
        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "Succesfuly deleted"));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> addUsers(@RequestBody UserDTO userDTO) {
        User u = new User();
        u.setName(userDTO.getName());
        u.setSurname(userDTO.getSurname());
        u.setPassword(userDTO.getPassword());
        userService.addUser(u);
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId(u.getId());
        userDTO1.setName(u.getName());
        userDTO1.setSurname(u.getSurname());

        return ResponseEntity.ok(ResponseDTO.of(userDTO1, "Successfully added"));
    }
}
