package com.springRESTfulApi.SPRING.BOOT.RESTful.api.Controller;

import com.springRESTfulApi.SPRING.BOOT.RESTful.api.Model.User;
import com.springRESTfulApi.SPRING.BOOT.RESTful.api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // getting user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<User> showSingleData(@PathVariable int id) {

        User user = userService.showSingleData(id);

        try {
            if (user == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // showing multiple user
    @GetMapping("/user")
    public ResponseEntity<List<User>> showMultipleData() {

        try {
            List<User> list = userService.showMultipleData();
            if (list.size() <= 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.ok(list);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // adding single user
    @PostMapping("/user")
    public ResponseEntity<User> addSingleData(@RequestBody User user) {

        try {
            userService.saveData(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // adding multiple user through list
    @PostMapping("/saveMultipleUser")
    public ResponseEntity<List<User>> addMultipleData(@RequestBody List<User> userList) {
        try {
            userService.saveMultipleData(userList);
            return ResponseEntity.ok(userList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // udpading user by id
    @PutMapping("user/{id}")
    public ResponseEntity<User> putMethodName(@PathVariable int id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // deleting user by id
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteById(@PathVariable int id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok(userService.showSingleData(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // deleting all user
    @DeleteMapping("/deleteAllData")
    public ResponseEntity<List<User>> deleteAllUser() {
        List<User> list = userService.showMultipleData();

        try {
            userService.deleteAllUser();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
