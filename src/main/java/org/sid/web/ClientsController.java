package org.sid.web;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Client;
import org.sid.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClientsController {
	
	Logger LOG = LoggerFactory.getLogger(ClientsController.class);
	
	@Autowired
	private  ClientRepository clientRepository;

	@RequestMapping(value="/getClients")
	public String consulterClients(){
		
		return "listClients";
	}
	
	
	@RequestMapping(value="/list-clients" , method = RequestMethod.GET)
	@ResponseBody
	public List<Client> listClients(){
		List<Client> listClients= clientRepository.findAll();
		return listClients;
	}
	
	@RequestMapping(value="/supprimer/{code}", method = RequestMethod.GET)
	@Transactional
	public String supprimerEtudiant(@PathVariable("code") Long code){
		LOG.info("supprimer", code);
		clientRepository.deleteByCode(code);
		return "redirect:/clients";
	}
	
	@RequestMapping(value="/modifier/{code}", method = RequestMethod.GET)
	public String modifierEtudiant(@PathVariable("code") Long code,Model model){
		LOG.info("getting user with id: {}", code);
		Optional<Client> clt=clientRepository.findByCode(code);
		model.addAttribute("client",clt);
		return "editerClient";
	}
	
	@RequestMapping(value="/updateClient",method=RequestMethod.POST)
	public String modifierEtudiant(Client client){
		
		LOG.info("etudiant à mettre à jour: "+client);
//		//etudiant existe dans le contexte de persistence
//		etudiantRepository.save(etudiant);
//		// on vide le context dans la base de donné
//		etudiantRepository.flush();
//		//etudiant est toujours dans le context donc le nom sera 3arbi
//		etudiant.setNom("3arbi");
		
		Client clt= clientRepository.getOne(client.getCode());
		clt.setNom(client.getNom());
		clt.setEmail(client.getEmail());
		clientRepository.save(clt);
		return "redirect:/getClients";
	}
	
	/*
	@RequestMapping(value="/visualiser/{id}")
	@ResponseBody
	public String visualiserEtudiant(@PathVariable("id")Long id){
		
		return "razine";
	}
*/
}
