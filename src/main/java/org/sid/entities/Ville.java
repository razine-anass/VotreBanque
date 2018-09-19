package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Classe represantante Ville
 * @author Razine anass
 *
 */
@Entity
//@NoArgsConstructor
//@Data
public class Ville implements Serializable  {
	@Id
	@GeneratedValue
	private Long id_ville;
	private String nom_ville;
	
	public Ville() {
		super();
	}
	/**
	 * Constructeur Ville
	 * @param nom_ville
	 */
	public Ville(String nom_ville) {
		super();
		this.nom_ville = nom_ville;
	}
	public Ville(Long id_ville, String nom_ville) {
		super();
		this.id_ville = id_ville;
		this.nom_ville = nom_ville;
	}
	
	
	public Long getId_ville() {
		return id_ville;
	}
	public void setId_ville(Long id_ville) {
		this.id_ville = id_ville;
	}
	public String getNom_ville() {
		return nom_ville;
	}
	public void setNom_ville(String nom_ville) {
		this.nom_ville = nom_ville;
	}
	
	
}
