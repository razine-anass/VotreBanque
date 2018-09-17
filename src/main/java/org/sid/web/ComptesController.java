package org.sid.web;

import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ComptesController est la classe représentante 
 * du controleur d'entité Compte.
 * @author Razine anass
 *
 */
@Controller
public class ComptesController {
	
	/**
	 * Permet de consulter les comptes.
	 * @return 
	 *        la page à afficher.
	 */
	@RequestMapping(value="/getComptes")
	public String consulterComptes(){
        return "listComptes";		
	}
	/**
	 * Affiche le fomulaire de création des comptes.
	 * @param model
	 *            transfet un objet client vers la couche web. 
	 * @return
	 *        la page du formulaire.
	 */
	@RequestMapping(value="/formCompte")
	public String afficherFromCompte(){
		return "formCompte";
	}
	/**
	 * 
	 * @param client
	 *              est l'objet à enregistrer.
	 * @return
	 */
	@RequestMapping(value="/enregistrerCompte",method=RequestMethod.POST)
	public String creerCompte(String typeCompte,String nom){
		nom.toString();
		return "creerCompte";
	}

}
