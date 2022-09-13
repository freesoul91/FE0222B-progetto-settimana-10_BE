package it.epicode.be.segreteria.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.segreteria.model.Corso;
import it.epicode.be.segreteria.model.Segreteria;
import it.epicode.be.segreteria.model.Studente;


@Controller
public class SegreteriaController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private ApplicationContext ctx;
	
	public Segreteria getSegreteria() {
		return ctx.getBean(Segreteria.class);
	}
	
	@GetMapping("/studenti")
	public String listaStudenti( Model model ) {
		List<Studente> listaStudenti = getSegreteria().getListaStudenti();
		
		model.addAttribute("listaStudenti", listaStudenti);
		log.info("Lista Studenti: " + listaStudenti.toString());
		return "listaStudenti";
	}
	
	@GetMapping("/corsi")
	public String listaCorsi( Model model ) {
		List<Corso> listaCorsi = getSegreteria().getListaCorsi();
		
		model.addAttribute("listaCorsi", listaCorsi);
		
		return "listaCorsi";
	}
	
	@RequestMapping(value="/studente", method=RequestMethod.GET)
	public ModelAndView showForm(Model model) {
		log.info("Richiesta form inserimento nuovo Studente");
		model.addAttribute("listaCorsi", getSegreteria().getListaCorsi());
		return new ModelAndView("formStudente", "studente", new Studente());
		
	}
	
	@PostMapping(value="/addStudente")
	public String submit(@ModelAttribute("studente") Studente studente, ModelMap model) {
		
			
		model.addAttribute("nome", studente.getNome());
		model.addAttribute("cognome", studente.getCognome());
		model.addAttribute("dataNascita", studente.getDataNascita());
		model.addAttribute("indirizzo", studente.getIndirizzo());
		model.addAttribute("citta", studente.getCitta());
		model.addAttribute("mail", studente.getMail());
		model.addAttribute("matricola", studente.getMatricola());	
		
		
		//Corso corsoScelto = (Corso)model.getAttribute("corso");
		
		model.addAttribute("corso", studente.getCorso()); 
		
		log.info("Hai inserito lo studente: " + studente.toString());
		getSegreteria().getListaStudenti().add(studente);
		
		return "studenteView";
	}
	
	@RequestMapping(value="/editstudente", method=RequestMethod.GET)
	public ModelAndView editForm(Model model) {
		List<Corso> listaCorsi = getSegreteria().getListaCorsi();
		model.addAttribute("listaCorsi", listaCorsi);
		return new ModelAndView("editStudente", "studente", new Studente());
		
	}
	
	//@PutMapping("/updateStudente")
	@RequestMapping(value="/updateStudente", method=RequestMethod.GET)
	public String update(@ModelAttribute("studente") Studente studente,@ModelAttribute("corso") Corso corso, ModelMap model, BindingResult result, Model m) {
		/*if (result.hasErrors()) {		
			return "error";
		}*/	
		
		List<Studente> listaStudenti = getSegreteria().getListaStudenti();
		for (Studente s : listaStudenti) {
			if ( s.getMatricola() == studente.getMatricola() ) {
				
				
				model.addAttribute("matricola", s.getMatricola());
				model.addAttribute("nome", studente.getNome());
				model.addAttribute("cognome", studente.getCognome());
				model.addAttribute("dataNascita", studente.getDataNascita());
				model.addAttribute("indirizzo", studente.getIndirizzo());
				model.addAttribute("citta", studente.getCitta());
				model.addAttribute("mail", studente.getMail());
				
				
				model.addAttribute("corso", studente.getCorso());
				listaStudenti.remove(s);
				
				s.setMatricola(s.getMatricola());
				s.setNome(studente.getNome());
				s.setCognome(studente.getCognome());
				s.setDataNascita(studente.getDataNascita());
				s.setIndirizzo(studente.getIndirizzo());
				s.setCitta(studente.getCitta());
				s.setMail(studente.getMail());
				listaStudenti.add(studente);

				
				log.info("Hai modificato lo studente: " + studente.toString());
				
				model.addAttribute("listaStudenti", listaStudenti);
				
			}
		}
		return "listaStudenti";	
	}
	
	@RequestMapping(value="/eliminastudente", method=RequestMethod.GET)
	public ModelAndView eliminaForm() {
		return new ModelAndView("deleteStudente", "studente", new Studente());
		
	}
	
	//@DeleteMapping("/deleteStudente")
	@RequestMapping(value="/deleteStudente", method=RequestMethod.GET)
	public String delete(@ModelAttribute("studente") Studente studente, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			
			return "error";
		}
		
		List<Studente> listaStudenti = getSegreteria().getListaStudenti();
		for (Studente s : listaStudenti) {
			if ( s.getMatricola() == studente.getMatricola() ) {
				log.info("matricola s: " + s.getMatricola());
				log.info("matricola studente: " + studente.getMatricola());
				listaStudenti.remove(s);
				
				model.addAttribute("listaStudenti", listaStudenti);
				
				
				log.info("Hai eliminato lo studente: " + s.toString());
				
			}
	
		}
		return "listaStudenti";
	}
	
	@RequestMapping(value="/corso", method=RequestMethod.GET)
	public ModelAndView showFormCorso() {
		log.info("Richiesta form inserimento nuovo Corso");
		return new ModelAndView("formCorso", "corso", new Corso());
		
	}
	
	@PostMapping(value="/addCorso")
	public String submit(@ModelAttribute("corso") Corso corso, ModelMap model, BindingResult result) {
		
		if (result.hasErrors()) {
			
			return "error";
		}
			
		model.addAttribute("idCorso", corso.getIdCorso());
		model.addAttribute("materia", corso.getMateria());
		model.addAttribute("indirizzoStudio", corso.getIndirizzoStudio());
		model.addAttribute("numeroEsami", corso.getNumeroEsami());
		
		log.info("Hai inserito il Corso: " + corso.toString());
		
		getSegreteria().getListaCorsi().add(corso);
		log.info("numero corsi disponibil: " + getSegreteria().getListaCorsi().size());
		
		model.addAttribute("listaCorsi", getSegreteria().getListaCorsi());
		return "listaCorsi";
	}
	
	@RequestMapping(value="/editcorso", method=RequestMethod.GET)
	public ModelAndView editFormCorso() {
		return new ModelAndView("editCorso", "corso", new Corso());
		
	}
	
	
	@RequestMapping(value="/eliminacorso", method=RequestMethod.GET)
	public ModelAndView eliminaFormCorso() {
		return new ModelAndView("deleteCorso", "corso", new Corso());
		
	}
	
	@RequestMapping(value="/deleteCorso", method=RequestMethod.GET)
	public String delete(@ModelAttribute("corso") Corso corso, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			
			return "error";
		}
		
		List<Corso> listaCorsi = getSegreteria().getListaCorsi();
		for (Corso c : listaCorsi) {
			if ( c.getIdCorso() == corso.getIdCorso() ) {
				log.info("ID corso c: " + c.getIdCorso());
				log.info("ID Corso corso: " + corso.getIdCorso());
				listaCorsi.remove(c);
				
				model.addAttribute("listaCorsi", listaCorsi);
				
				
				log.info("Hai eliminato il Corso: " + c.toString());
				
			}
	
		}
		return "listaCorsi";
	}
	
	@RequestMapping(value="/updateCorso", method=RequestMethod.GET)
	public String update(@ModelAttribute("corso") Corso corso, ModelMap model, BindingResult result) {
		/*if (result.hasErrors()) {		
			return "error";
		}*/	
		List<Corso> listaCorsi = getSegreteria().getListaCorsi();
		for (Corso c : listaCorsi) {
			if ( c.getIdCorso() == corso.getIdCorso() ) {
				
				//CorsoConverter cC = new CorsoConverter();
				
				model.addAttribute("idCorso", c.getIdCorso());
				model.addAttribute("materia", corso.getMateria());
				model.addAttribute("indirizzoStudio", corso.getIndirizzoStudio());
				model.addAttribute("numeroEsami", corso.getNumeroEsami());
				
				listaCorsi.remove(c);

				getSegreteria().getListaCorsi().add(corso);
				
				log.info("Hai modificato il corso: " + corso.toString());
				
				model.addAttribute("listaCorsi", listaCorsi);
				
			}
		}
		return "listaCorsi";	
	}
	

}
