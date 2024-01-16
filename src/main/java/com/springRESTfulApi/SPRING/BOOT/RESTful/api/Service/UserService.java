package com.springRESTfulApi.SPRING.BOOT.RESTful.api.Service;

import com.springRESTfulApi.SPRING.BOOT.RESTful.api.Model.User;
import com.springRESTfulApi.SPRING.BOOT.RESTful.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveData(User user) {
        userRepository.save(user);
    }

    public void saveMultipleData(List<User> userList) {
        userRepository.saveAll(userList);
    }

    public void updateData(User user) {
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User showSingleData(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> showMultipleData() {
        return userRepository.findAll();
    }

    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    public User updateUser(int id, User user) {

        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Update the existing user with the new data
            existingUser.setId(user.getId());
            existingUser.setName(user.getName());

            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            // User not found
            return null;
        }
    }

}
