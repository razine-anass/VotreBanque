package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue
    private Long code;
	private String nom;
	private String email;
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY, cascade = CascadeType.ALL)//relation bidirectionnelle
	@JsonIgnore//ne sera pas sérialisé par jackson
	private Collection<Compte> comptes;
	public Client() {
		super();
	}
	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	
	

}
