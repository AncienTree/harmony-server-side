package pl.entpoint.harmony.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.entpoint.harmony.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
