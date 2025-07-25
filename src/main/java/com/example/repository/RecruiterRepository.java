package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{
	Recruiter findByEmail(String email);
	Optional<Recruiter> findById(Long id);

}
