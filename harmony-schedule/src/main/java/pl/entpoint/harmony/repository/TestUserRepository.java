package pl.entpoint.harmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.entpoint.harmony.entity.User;

public interface TestUserRepository extends JpaRepository<User, Integer>{

}
