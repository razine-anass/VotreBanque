package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe represantante le client
 * @author Razine anass
 *
 */
@Entity
//elle crée les setters et les getters
@Data
public class Client implements Serializable {
	@Id
	@GeneratedValue
    private Long code;
	private String nom;
	private String email;
	//relation bidirectionnelle
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
	@JsonIgnore//n'est pas sérialisé par jackson
	private Collection<Compte> comptes;
	@ManyToOne
	private Ville ville;
	
	public Client() {
		super();
	}
	/**
	 * Constructeur Client
	 * @param nom
	 * @param email
	 */
	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}
	/**
	 * Constructeur Client
	 * @param nom
	 * @param email
	 * @param ville
	 */
	public Client(String nom, String email, Ville ville) {
		super();
		this.nom = nom;
		this.email = email;
		this.ville = ville;
	}

}
