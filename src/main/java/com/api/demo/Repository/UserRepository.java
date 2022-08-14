package com.api.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.demo.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
