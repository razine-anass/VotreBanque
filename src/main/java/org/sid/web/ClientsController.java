package org.sid.web;

import java.util.List;

import org.sid.entities.Client;
import org.sid.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClientsController {
	
	Logger LOG = LoggerFactory.getLogger(ClientsController.class);
	
	@Autowired
	private  ClientRepository clientRepository;

	@RequestMapping(value="/clients")
	public String consulterClients(){
		
		return "listClients";
	}
	
	
	@RequestMapping(value="/list-clients" , method = RequestMethod.GET)
	@ResponseBody
	public List<Client> listClients(){
		List<Client> listClients= clientRepository.findAll();
		return listClients;
	}
	
	@RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
	@Transactional
	public String supprimerEtudiant(@PathVariable("id") Long id){
		LOG.info("supprimer", id);
		clientRepository.deleteByCode(id);
		return "redirect:/clients";
	}
	/*
	@RequestMapping(value="/modifier/{id}", method = RequestMethod.GET)
	public String modifierEtudiant(@PathVariable("id") Long id,Model model){
		LOG.info("getting user with id: {}", id);
		Optional<Etudiant> etd=etudiantRepository.findById(id);
		model.addAttribute("etudiant",etd);
		return "editer";
	}
	
	@RequestMapping(value="/updateEtudiant",method=RequestMethod.POST)
	public String modifierEtudiant(Etudiant etudiant){
		
		LOG.info("etudiant à mettre à jour: "+etudiant);
//		//etudiant existe dans le contexte de persistence
//		etudiantRepository.save(etudiant);
//		// on vide le context dans la base de donné
//		etudiantRepository.flush();
//		//etudiant est toujours dans le context donc le nom sera 3arbi
//		etudiant.setNom("3arbi");
		
		Etudiant etd= etudiantRepository.getOne(etudiant.getId());
		etd.setNom(etudiant.getNom());
		etd.setEmail(etudiant.getEmail());
		etd.setDateNaissance(etudiant.getDateNaissance());
		etudiantRepository.save(etd);
		return "redirect:/index";
	}
	
	@RequestMapping(value="/visualiser/{id}")
	@ResponseBody
	public String visualiserEtudiant(@PathVariable("id")Long id){
		
		return "razine";
	}
*/
}
