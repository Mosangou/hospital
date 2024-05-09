package com.mo.entities;

import java.util.Date;

import org.springframework.data.jpa.repository.Temporal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Patient {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@NotEmpty(message = "Username is required")
	@Size(min=4, max=30)
	private String nom;
	
	private Date dateNaissance;
	private boolean malade;
	@Min(50)
	private int score;
	
	public Patient(String nom, Date dateNaissance, boolean malade, int score) {
		super();
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.malade = malade;
		this.score = score;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public boolean isMalade() {
		return malade;
	}

	public void setMalade(boolean malade) {
		this.malade = malade;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	

}
