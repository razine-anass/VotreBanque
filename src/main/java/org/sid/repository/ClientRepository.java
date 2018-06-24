package org.sid.repository;

import java.util.Optional;

import org.sid.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long>{
	
	public void deleteByCode(Long code);
	
	public Optional<Client> findByCode(Long id);
	
//	
//public List<Etudiant> findByNom(String n);
//	
//	public Optional<Etudiant> findById(Long id);
//	
//	public List<Etudiant> findAll();
//	
//	public void deleteById(Long id);
//	
//	@Query("select e from Etudiant e where e.nom like :x")
//	public Page<Etudiant> chercherEtudiants(@Param("x") String mc, Pageable pageable);
//
//	@Query("select e from Etudiant e where e.dateNaissance >:x and e.dateNaissance <:y")
//	public List<Etudiant> chercherEtudiants(@Param("x")Date d1,@Param("y")Date d2);
}
