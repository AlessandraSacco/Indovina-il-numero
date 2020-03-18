package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco= false; //variabile che ci dice se siamo dentro o fuori dal gioco 
	//certe cose si possono fare soltanto se questa variabile sia vera o falsa
	// ha impatto sull'interfaccia utente perchè abilita o disabilita alcuni bottoni ad essere
	//attivi
	
	private Set<Integer> tentativi;
	
	public Model() {
		this.inGioco = false;
		this.tentativiFatti =0;
		}
	
	public void nuovaPartita() {
		// gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti=0;
    	this.inGioco= true;
    	this.tentativi = new HashSet<Integer>();
    	
	}
	
	public int tentativo(int tentativo) {
		// controllo se la partita e' in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è gia terminata");
		}
			// Controllo l'imput 
			if(!tentativoValido(tentativo)) {
				throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e " + NMAX);
				
				}
			
			//il tentativo è valido ---->possiamo "provarlo"
			this.tentativiFatti++;
			this.tentativi.add(tentativo);
			if(this.tentativiFatti == TMAX) {
				this.inGioco= false;
			}
			
			if(tentativo == this.segreto) {
				this.inGioco= false;
				return 0;
			}
		
			if(tentativo < this.segreto) {
				return -1;
			} else {
				return 1;
			}
		}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo <1 || tentativo > NMAX) {
			return false;
			
		}
		else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			return true;
	}
}

	public int getSegreto() {
		return segreto;
	}

	public void setSegreto(int segreto) {
		this.segreto = segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public void setTentativiFatti(int tentativiFatti) {
		this.tentativiFatti = tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	}


