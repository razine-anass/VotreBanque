package org.sid.metier;

import java.util.Date;

import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.repository.CompteRepository;
import org.sid.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional// toute les methode sont transactionnel soit elle s'excutent jusqu'au bout
// ou non
public class BanqueMetierImpl implements IBanqueMetier{
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
          Compte cp=compteRepository.findOne((codeCpte));
          if(cp==null) throw new RuntimeException("Compte introuvable");
        return cp;
	}

	@Override
	public void verser(String codecpte, double montant) {
             Compte cp=consulterCompte(codecpte);
             Versement v=new Versement(new Date(), montant, cp);
             operationRepository.save(v);
             cp.setSolde(cp.getSolde()+montant);
             compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp=consulterCompte(codeCpte);
		double faciliteCaisse=0;
		
		if (cp instanceof CompteCourant) 
		faciliteCaisse=((CompteCourant)cp).getDecouvert();
		if(cp.getSolde()+faciliteCaisse<montant)
			throw new RuntimeException("Solde insuffisant");
        Retrait r=new Retrait(new Date(), montant, cp);
        operationRepository.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		
		if(codeCpte1.equals(codeCpte2)){
			throw new RuntimeException("Impossible virement sur le meme compte");
		}
		retirer(codeCpte1,montant);
		verser(codeCpte2,montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
	
		return operationRepository.listOperation(codeCpte, new PageRequest(page,size));
	}

}
