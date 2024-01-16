package com.springRESTfulApi.SPRING.BOOT.RESTful.api.Repository;

import com.springRESTfulApi.SPRING.BOOT.RESTful.api.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
