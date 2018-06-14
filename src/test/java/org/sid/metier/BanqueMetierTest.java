package org.sid.metier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.hamcrest.number.IsCloseTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sid.VotreBanqueApplication;
import org.sid.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VotreBanqueApplication.class)
@Transactional
public class BanqueMetierTest {
	
	@Autowired
	IBanqueMetier banqueMetier;
	
	@Test
	public void testConsulterCompte(){
		String codeCpte="c1";
		Compte cp=banqueMetier.consulterCompte(codeCpte);
		assertThat(cp.getCodeCompte().equals(codeCpte));
	}
	
	@Test
	public void testVerser(){
		String codecpte="c1";
		Compte cp=banqueMetier.consulterCompte(codecpte);
		double solde=cp.getSolde();
		double montant=500;
		banqueMetier.verser(codecpte, montant);
		double newSolde=solde+500;
		assertThat(newSolde, new IsCloseTo(cp.getSolde(), 0.1)); 
	}
	@Test
	public void testRetirer(){
		String codecpte="c1";
		Compte cp=banqueMetier.consulterCompte(codecpte);
		double solde=cp.getSolde();
		double montant=500;
		banqueMetier.retirer(codecpte, montant);
		double newSolde=solde-500;
		assertThat(newSolde, new IsCloseTo(cp.getSolde(), 0.1)); 
	}
	@Test
	public void testVirement(){
		String codecpte="c1";
		Compte cp=banqueMetier.consulterCompte(codecpte);
		double solde=cp.getSolde();
		String codecpte2="c2";
		Compte cp2=banqueMetier.consulterCompte(codecpte2);
		double solde2=cp2.getSolde();
		double montant=1000;
		banqueMetier.virement(codecpte, codecpte2, montant);
		double newSolde=solde-1000;
		double newSolde2=solde2+1000;
		assertThat(newSolde, new IsCloseTo(cp.getSolde(), 0.1)); 
		assertThat(newSolde2, new IsCloseTo(cp2.getSolde(), 0.1)); 
	}
}
