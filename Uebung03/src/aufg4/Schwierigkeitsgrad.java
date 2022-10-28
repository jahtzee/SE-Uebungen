/**
 * 
 */
package aufg4;

/**
 * @author Jan Zimmer
 * last modified 28.10.2022
 */
public class Schwierigkeitsgrad {

	private int gewinnsumme;
	private int prozentsatz;
	
	public Schwierigkeitsgrad(int gewinnsumme, int prozentsatz) {
		this.gewinnsumme = gewinnsumme;
		this.prozentsatz = prozentsatz;
	}

	public int getGewinnsumme() {
		return gewinnsumme;
	}

	public void setGewinnsumme(int gewinnsumme) {
		this.gewinnsumme = gewinnsumme;
	}

	public int getProzentsatz() {
		return prozentsatz;
	}

	public void setProzentsatz(int prozentsatz) {
		this.prozentsatz = prozentsatz;
	}

	
}
