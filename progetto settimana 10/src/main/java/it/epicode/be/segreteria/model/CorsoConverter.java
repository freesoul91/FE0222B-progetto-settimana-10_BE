package it.epicode.be.segreteria.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.segreteria.config.SegreteriaConfig;
import it.epicode.be.segreteria.util.GestioneSegreteria;


@Component
public class CorsoConverter implements Converter<String, Corso> {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	ApplicationContext ctx;
	
	/*@Autowired
	public CorsoConverter() {
		//ctx = new AnnotationConfigApplicationContext(SegreteriaConfig.class); 
		Segreteria segreteria = ctx.getBean(Segreteria.class); 
	}*/
	
	/*@Override
	public Corso convert(String idCorso) {
		Segreteria segreteria = (Segreteria)ctx.getBean("segreteria");
		Corso corso = null;
		List<Corso> listaCorsi = segreteria.getListaCorsi();
		
		for (Corso c : listaCorsi ) {
			if ( Integer.toString(c.getIdCorso()).equals(idCorso) ) {
				log.info("ID CORSO CorsoConverterClass(nell'if): " + idCorso);
				corso = c;
				log.info("IdCorso linea 39 CorsoConverter: " + corso.getIdCorso());
			}
			
		}	
		log.info("ID corso corsoConverterClass(dopo l'if): " + corso.getIdCorso() + " Materia: " + corso.getMateria());
		return corso;
	}*/
	
	/*public Corso corsoById(int idCorso) {
		Corso corso = null;
		List<Corso> listaCorsi = ctx.getBean(Segreteria.class).getListaCorsi();
		for (Corso c : listaCorsi) {
			if (c.getIdCorso() == idCorso) {
				corso = c;
			}
			
		}
		return corso;
	}*/
	
	@Override
	public Corso convert(String idCorso) {
		log.info("ID corsoConverter: " + idCorso);
		return getCorsoById(idCorso);
	}
	
	public Corso getCorsoById(int idCorso) {
		log.info("Id corso converter: " + idCorso);
		Corso corso = null;
		for (Corso c : ctx.getBean(Segreteria.class).getListaCorsi()) {
			if (c.getIdCorso() == idCorso) {
				corso = c;
			}
		}
		return corso;
	}
	
	public Corso getCorsoById(String idCorso) {
		log.info("Id corso converter2: " + idCorso);
		return getCorsoById(Integer.parseInt(idCorso));
	}

}
