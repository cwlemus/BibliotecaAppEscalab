package com.escalab.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.biblioteca.model.ResetToken;

public interface IResetTokenRepo extends JpaRepository<ResetToken, Integer> {
	
	//from ResetToken where token = :?
	ResetToken findByToken(String token);

}

