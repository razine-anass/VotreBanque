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

/**
 * ClientsController est la classe représentante 
 * du controleur d'entité Client.
 * @author Razine anass
 *
 */
@Controller
public class ClientsController {
	
	Logger LOG = LoggerFactory.getLogger(ClientsController.class);
	
	/**
	 * Injection repository Client.
	 */
	@Autowired
	private  ClientRepository clientRepository;
	
	@RequestMapping(value="/afficher")
	public String afficher(){
		return "nav";
	}

	/**
	 * Retourne la liste des clients.
	 * @return 
	 *        la page listClients.
	 */
	@RequestMapping(value="/getClients")
	public String consulterClients(){
		return "listClients";
	}
	/**
	 * Retourne la liste des clients dans le corps
	 * de la requete.
	 * @return 
	 *        liste des clients.
	 */
	@RequestMapping(value="/list-clients" , method = RequestMethod.GET)
	@ResponseBody
	public List<Client> listClients(){
		List<Client> listClients= clientRepository.findAll();
		return listClients;
	}
	/**
	 * Supprime le client par le code.
	 * @param code 
	 *            code du client à supprimer.
	 * @return
	 *        le chemin de la page à afficher.
	 */
	@RequestMapping(value="/supprimer/{code}", method = RequestMethod.GET)
	@Transactional
	public String supprimerClient(@PathVariable("code") Long code){
		LOG.info("client à supprimer", code);
		clientRepository.deleteByCode(code);
		return "redirect:/clients";
	}
	/**
	 * Modifie le client par le code.
	 * @param code
	 *            code du client à modifier.
	 * @param model 
	 *            permet de recuperer le client à modifier.
	 * @return 
	 *        le chemin de la page à afficher.
	 */
	@RequestMapping(value="/modifier/{code}", method = RequestMethod.GET)
	public String modifierClient(@PathVariable("code") Long code,Model model){
		LOG.info("code du client", code);
		Optional<Client> clt=clientRepository.findByCode(code);
		model.addAttribute("client",clt);
		return "editerClient";
	}
	/**
	 * Modifie le client.
	 * @param client
	 *              le client à modifier.
	 * @return
	 *        le chemin de la page à afficher.
	 */
	@RequestMapping(value="/updateClient",method=RequestMethod.POST)
	public String modifierClient(Client client){
		LOG.info("client à mettre à jour: ",client);
		Client clt= clientRepository.getOne(client.getCode());
		clt.setNom(client.getNom());
		clt.setEmail(client.getEmail());
		clientRepository.save(clt);
		return "redirect:/getClients";
	}
}
