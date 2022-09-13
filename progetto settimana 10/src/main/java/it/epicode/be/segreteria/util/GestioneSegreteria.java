package it.epicode.be.segreteria.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import it.epicode.be.segreteria.model.Corso;
import it.epicode.be.segreteria.model.CorsoChimica;
import it.epicode.be.segreteria.model.CorsoFisica;
import it.epicode.be.segreteria.model.CorsoLettere;
import it.epicode.be.segreteria.model.Segreteria;
import it.epicode.be.segreteria.model.Studente;

@Component
public class GestioneSegreteria implements CommandLineRunner {
	
	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public void run(String... args) throws Exception {
		Segreteria segreteria = ctx.getBean(Segreteria.class);
		popolaStudenti(segreteria);
		stampaListe(segreteria);
		
	}
	
	public void stampaListe(Segreteria segreteria) {
		System.out.println("******* Segreteria *******");
		System.out.println("Studenti");
		//popolaStudenti();
		segreteria.getListaStudenti().forEach(s -> System.out.println(s.toString()));
		System.out.println();
		System.out.println("Corsi");
		segreteria.getListaCorsi().forEach(c -> System.out.println(c.toString()));
	}
	
	public void popolaStudenti(Segreteria segreteria) {
		Corso fisica = (CorsoFisica)ctx.getBean("corsofisica");
		Corso lettere = (CorsoLettere)ctx.getBean("corsolettere");
		Corso chimica = (CorsoChimica)ctx.getBean("corsochimica");
		segreteria.getListaCorsi().add(fisica);
		segreteria.getListaCorsi().add(lettere);
		segreteria.getListaCorsi().add(chimica);
		
		Studente s1 = new Studente(1, "Daniele", "Cerulli", LocalDate.parse("1992-03-13"), "danielecerulli@libero.it", "via E. Buccarelli 119", "Fiumicino", fisica);
		Studente s2 = new Studente(2, "Gianni", "Celeste", LocalDate.parse("1964-07-11"), "g.celeste@gmail.com", "via Gigi 10", "Napoli", lettere);
		Studente s3 = new Studente(3, "Ugo", "Rossi", LocalDate.parse("1981-11-10"), "ugo.rossi@yahoo.it", "via Giolitti 3", "Roma", chimica);
		segreteria.getListaStudenti().add(s1);
		segreteria.getListaStudenti().add(s2);
		segreteria.getListaStudenti().add(s3);
	}

	

}
