package org.sid.web;

import javax.servlet.http.HttpServletRequest;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IBanqueMetier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
	
	Logger log = LoggerFactory.getLogger(BanqueController.class);
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping(value="/operations")
	public String index(){
		return "comptes";
	}
	
	@RequestMapping(value="/consulterCompte",method=RequestMethod.GET)
	public String consulter(Model model,String codeCompte,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp=banqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperations=banqueMetier.listOperation(codeCompte, page, size);
			model.addAttribute("listeOperations", pageOperations.getContent());
			int[] pages=new int[pageOperations.getTotalPages()];
			model.addAttribute("pages", pages);
			// a revoir 
//			model.addAttribute("listeOperations", compteRepository.findOne(codeCompte).getOperations());
			model.addAttribute("compte", cp);
		} catch (Exception e){
			model.addAttribute("exception", e);
		}
		
		return "comptes";
	}
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
	public String saveOperation(Model model,HttpServletRequest request,String typeOperation,String codeCompte,double montant,String codeCompte2){
		try{
			if(typeOperation.equals("VERS")){
				banqueMetier.verser(codeCompte, montant);
			}else if(typeOperation.equals("RET")){
				banqueMetier.retirer(codeCompte, montant);
			}
			if(typeOperation.equals("VIR")){
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
			
		}catch(Exception e){
			model.addAttribute("error", e);
			log.error("toto",e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		//on envoi la reponse au client et on lui demande de se rediriger vers
		//  /consulterCompte donc le model se vide et on perd le message d'error
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}

}
