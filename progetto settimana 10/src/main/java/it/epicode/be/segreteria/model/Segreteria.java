package it.epicode.be.segreteria.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Segreteria {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private List<Studente> listaStudenti = new ArrayList<>();
	private List<Corso> listaCorsi = new ArrayList<>();
	
	public List<Studente> getListaStudenti() {
		return listaStudenti;
	}
	public List<Corso> getListaCorsi() {
		return listaCorsi;
	}
	public void setListaStudenti(List<Studente> listaStudenti) {
		this.listaStudenti = listaStudenti;
	}
	public void setListaCorsi(List<Corso> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}
	
	
}
