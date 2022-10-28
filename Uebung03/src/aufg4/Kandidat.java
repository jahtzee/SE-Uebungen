/**
 * 
 */
package aufg4;

import java.util.ArrayList;

/**
 * @author Jan Zimmer
 * last modified 28.10.2022
 */
public class Kandidat {

	private String name;
	private String stadt;
	private String beruf;
	private String datum;
	private ArrayList<Antwort> kandidatAntworten;
	
	public Kandidat(String name, String stadt, String beruf, String datum) {
		this.name = name;
		this.stadt = stadt;
		this.beruf = beruf;
		this.datum = datum;
		this.kandidatAntworten = new ArrayList<Antwort>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getBeruf() {
		return beruf;
	}

	public void setBeruf(String beruf) {
		this.beruf = beruf;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public ArrayList<Antwort> getKandidatAntworten() {
		return kandidatAntworten;
	}
	
}
