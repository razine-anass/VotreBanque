package org.sid;

import java.util.Date;

import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.metier.IBanqueMetier;
import org.sid.repository.ClientRepository;
import org.sid.repository.CompteRepository;
import org.sid.repository.OperationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VotreBanqueApplication  {


	public static void main(String[] args) {
		
		ApplicationContext	ctx=SpringApplication.run(VotreBanqueApplication.class, args);
//		ClientRepository clientRepository=ctx.getBean(ClientRepository.class);
//		CompteRepository compteRepository=ctx.getBean(CompteRepository.class);
//		OperationRepository operationRepository=ctx.getBean(OperationRepository.class);
//		IBanqueMetier banqueMetier=ctx.getBean(IBanqueMetier.class);
//		
//		Client c1=clientRepository.save(new Client("Kassem","kassem@hotmail.fr"));
//		Client c2=clientRepository.save(new Client("l3arbaoui","l3arbaoui@hotmail.fr"));
//		
//         Compte cp1=compteRepository.save(new CompteCourant("c1",new Date(),9000,c1,6000));
//         Compte cp2=compteRepository.save(new CompteEpargne("c2",new Date(),6000,c2,5.5));
//        operationRepository.save(new Versement(new Date(), 9000,cp1));
//        operationRepository.save(new Versement(new Date(), 6000,cp1));
//        operationRepository.save(new Versement(new Date(), 2300,cp1));
//        operationRepository.save(new Retrait(new Date(), 9000,cp1));
//        
//
//        operationRepository.save(new Versement(new Date(), 2300,cp2));
//        operationRepository.save(new Versement(new Date(), 400,cp2));
//        operationRepository.save(new Versement(new Date(), 2300,cp2));
//        operationRepository.save(new Retrait(new Date(), 3000,cp2));
//        
//        banqueMetier.verser("c1", 10000);
	}

}

