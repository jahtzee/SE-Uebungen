/**
 * 
 */
package aufg4;

import java.util.ArrayList;

/**
 * @author Jan Zimmer
 * last modified 28.10.2022
 */
public class Sendung {

	private int id;
	private String datum;
	private ArrayList<Kandidat> kandidaten;
	
	public Sendung(int id, String datum) {
		this.id = id;
		this.datum = datum;
		kandidaten = new ArrayList<Kandidat>();
	}


	public void erfasseAntwort(Kandidat k, Antwort a) {
		if (!(this.kandidaten.contains(k)))
				this.getKandidaten().add(k);
		k.getKandidatAntworten().add(a);
	}

	public void printUebersicht() {
		System.out.println("Sendungsid: " + this.getId() + " Datum: " + this.getDatum() + " \n"
				+ "Kandidaten: ");
		for (Kandidat e : this.getKandidaten()) {
			System.out.println(e.getName() + " aus " + e.getStadt() + ", von Beruf " + e.getBeruf());
		}
		System.out.println("Bisherige Antworten: ");
		for (Kandidat e : this.getKandidaten()) {
			System.out.println("Kandidat " + e.getName() + " gab bisher folgende Antworten: ");
			for (Antwort a : e.getKandidatAntworten()) {
				System.out.println(a.getString() + ". Diese Antwort war " + booleanToString(a.istAntwortRichtig()) + ".");
			}
		}
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDatum() {
		return datum;
	}


	public void setDatum(String datum) {
		this.datum = datum;
	}

	public ArrayList<Kandidat> getKandidaten() {
		return this.kandidaten;
	}
	
	public String booleanToString(Boolean bool) {
		return (bool == true) ? "richtig" : "falsch";
	}
}
