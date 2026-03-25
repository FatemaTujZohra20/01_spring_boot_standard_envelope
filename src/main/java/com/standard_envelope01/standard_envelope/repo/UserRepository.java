package com.standard_envelope01.standard_envelope.repo;

import com.standard_envelope01.standard_envelope.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
