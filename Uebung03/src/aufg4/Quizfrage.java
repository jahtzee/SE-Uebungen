/**
 * 
 */
package aufg4;

import java.util.ArrayList;

/**
 * @author Jan Zimmer
 * last modified 28.10.2022
 */
public class Quizfrage {

	private int id;
	private String frage;
	private Wissensgebiet gebiet;
	private Schwierigkeitsgrad schwierigkeit;
	private ArrayList<Antwort> falscheAntworten;
	private Antwort richtigeAntwort;

	public Quizfrage(int id, String frage, Wissensgebiet gebiet, Schwierigkeitsgrad schwierigkeit) {
		this.id = id;
		this.frage = frage;
		this.gebiet = gebiet;
		this.schwierigkeit = schwierigkeit;
		this.falscheAntworten = new ArrayList<Antwort>();
	}

	public Antwort addRichtigeAntwort(int id, String string, String string2) {
		Antwort a = new Antwort(id, this, string, string2);
		this.richtigeAntwort = a;
		return a;
	}

	public Antwort addFalscheAntwort(int id, String string, String string2) {
		Antwort a = new Antwort(id, this ,string, string2);
		try {
			if (this.getFalscheAntworten().size() < 3) {
				this.getFalscheAntworten().add(a);
			} else {
				throw new IllegalAccessException();
			}
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		return a;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrage() {
		return frage;
	}

	public void setFrage(String frage) {
		this.frage = frage;
	}

	public Wissensgebiet getGebiet() {
		return gebiet;
	}

	public void setGebiet(Wissensgebiet gebiet) {
		this.gebiet = gebiet;
	}

	public Schwierigkeitsgrad getSchwierigkeit() {
		return schwierigkeit;
	}

	public void setSchwierigkeit(Schwierigkeitsgrad schwierigkeit) {
		this.schwierigkeit = schwierigkeit;
	}

	public ArrayList<Antwort> getFalscheAntworten() {
		return falscheAntworten;
	}


	public Antwort getRichtigeAntwort() {
		return richtigeAntwort;
	}

	
}
