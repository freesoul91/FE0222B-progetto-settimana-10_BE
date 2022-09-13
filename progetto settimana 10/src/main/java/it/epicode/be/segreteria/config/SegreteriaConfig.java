package it.epicode.be.segreteria.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.epicode.be.segreteria.model.Corso;
import it.epicode.be.segreteria.model.CorsoChimica;
import it.epicode.be.segreteria.model.CorsoFisica;
import it.epicode.be.segreteria.model.CorsoLettere;
import it.epicode.be.segreteria.model.Segreteria;
import it.epicode.be.segreteria.model.Studente;

@Configuration
public class SegreteriaConfig {
	
	@Bean
	public Segreteria segreteria() {
		return new Segreteria();	
	}
	
	@Bean
	@Scope("prototype")
	public Studente studente() {	
		return new Studente();
	}
	@Bean
	@Scope("prototype")
	public Corso corso() {
		return new Corso();
	}
	
	@Bean 
	public CorsoFisica corsofisica() {
		return new CorsoFisica();
	}
	
	@Bean 
	public CorsoLettere corsolettere() {
		return new CorsoLettere();
	}
	
	@Bean 
	public CorsoChimica corsochimica() {
		return new CorsoChimica();
	}
	
	
}
