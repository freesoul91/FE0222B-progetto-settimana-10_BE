package it.epicode.be.segreteria.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Corso {
	
	
	private int idCorso;
	@NotEmpty(message = "Inserisci il nome della materia!")
	private String materia;	
	private String indirizzoStudio;
	@Min(10)
	private int numeroEsami;
	
	@Override
	public String toString() {
		return "Corso [idCorso: " + idCorso + ", materia: " + materia + ", indirizzoStudio: " + indirizzoStudio
				+ ", numeroEsami: " + numeroEsami + "]";
	}
	
	

}
