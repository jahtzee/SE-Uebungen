import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 
 */

/**
 * @author Jan Zimmer
 * last modified 18.11.2022
 * 
 * Diese Klasse enth�lt Logik zum Betrieb einer Wetterstationszentrale. Angeh�rige Nested-Classes umfassen
 * einzelne Wetterstationen, Thermometer und Wetterdaten.
 * 
 * Die main() Methode enth�lt ein Testszenario zum Errechnen der Durchschnittstemperatur �ber alle validen Datenpunkte der
 * zur Zentrale zugeh�rigen Wetterstationen, sowie Grenzwert-Tests f�r die isTempValid() und setErrorFlag() Methoden von Wetterstationen.
 * 
 * Zu Testzwecken werden den Thermometern bei der Instanziierung zuf�llige Temperaturen oder fehlerhafte Werte zugeordnet.
 *  
 */

public class Zentrale {
	
	private Set<Wetterstation> stationen;

	public static void main(String[] args) {
		/*
		 * Testszenario
		 * 
		 * Zu erwartendes Ergebnis: Eine Ausgabe auf der Kommandozeile mit einer Durchschnittstemperatur mit zwei
		 * Dezimalstellen, der Anzahl der verwendeten Datenpunkte und Wetterstationen sowie der Anzahl an Wetterstationen
		 * mit einem Fehlercode.
		 * 
		 */
		Zentrale z1 = new Zentrale(1000);
		z1.printDurchschnittsTemperatur();
		
		System.out.println("\n#####################\n");
		
		/*
		 * Wetterstation::isTempValid()
		 * 
		 */
		System.out.println(Wetterstation.isTempValid(-100.1)); //False
		System.out.println(Wetterstation.isTempValid(-100.0)); // True
		System.out.println(Wetterstation.isTempValid(120.1)); //False
		System.out.println(Wetterstation.isTempValid(-100.0)); //True
		
		System.out.println("\n#####################\n");
		/*
		 * Wetterstation::setErrorFlag()
		 * 
		 */
		Zentrale z2 = new Zentrale();
		Wetterstation w = z2.new Wetterstation();
		z2.stationHinzufuegen(w);
		System.out.println(w.getErrorFlag()); //False
		w.boden.setTemp(null);
		System.out.println(w.getErrorFlag()); //True
		w.boden.setTemp(121.0);
		System.out.println(w.getErrorFlag()); //True
		w.boden.setTemp(120.0);
		System.out.println(w.getErrorFlag()); //False
	}

	/*
	 * Konstruktor f�r eine Wetterzentrale mit n Wetterstationen
	 */
	public Zentrale(int n) {
		this.stationen = new HashSet<Wetterstation>();
		for (int i = 1; i<=n; i++) {
			this.stationen.add(new Wetterstation());
		}
	}
	
	/*
	 * Default Konstruktor
	 */
	public Zentrale() {
		this.stationen = new HashSet<Wetterstation>();
	}
	
	public void stationHinzufuegen(Wetterstation w) {
		stationen.add(w);
	}

	public void stationEntfernen(Wetterstation w) {
		stationen.remove(w);
	}
	
	public void printDurchschnittsTemperatur() {
		int error = 0;
		int counter = 0;
		Double ergebnis = 0.0;
		for (Wetterstation w : stationen) {
			if (w.getErrorFlag() == false) {
				ergebnis += w.getWetterdaten().bodenTemp;
				ergebnis += w.getWetterdaten().HoehenTemp;
				counter += 2;
			} else {
				error += 1;
			}
		}
		ergebnis = ergebnis/counter;
		System.out.println("Die Durchschnittstemperatur betr�gt: "+Math.round(ergebnis*100.0)/100.0+" Grad Celsius \n"
				+ "Es wurden "+counter+" Datenpunkte aus "+counter/2+" Wetterstationen ber�cksichtigt. \n"
				+ error+" Wetterstationen meldeten einen Fehler.");
	}
	/*
	 * Jede Wetterstation verf�gt �ber genau zwei Thermometer. Wetterdaten k�nnen in Form
	 * von Werte-Paaren �ber getWetterdaten() abgefragt werden.
	 */
	public class Wetterstation {
		private Bodenthermometer boden;
		private Hoehenthermometer hoehe;
		private boolean errorFlag; //true --> Fehler liegt vor
		
		public Wetterstation() {
			this.boden = new Bodenthermometer();
			this.hoehe = new Hoehenthermometer();
			setErrorFlag();
		}
		
		/*
		 * ErrorFlag wird auf True gesetzt, falls eines unserer Temperatur Double Objekte null ist oder
		 * der Sanity Check fehlschl�gt.
		 */
		private void setErrorFlag() {
			if (boden.getTemperatur() != null && hoehe.getTemperatur() != null) {
				if (isTempValid(boden.getTemperatur()) && isTempValid(hoehe.getTemperatur())) {
					errorFlag = false;
				} else {
					errorFlag = true;
				}
			} else {
				errorFlag = true;
			}
		}
		
		public Wetterdaten getWetterdaten() {
			return new Wetterdaten(boden.getTemperatur(), hoehe.getTemperatur());
		}
		
		public boolean getErrorFlag() {
			setErrorFlag(); //Erst pr�fen, dann ausgeben
			return errorFlag;
		}
		
		public static boolean isTempValid(Double temp) {
			return (temp <= 120 && temp >= -100) ? true : false; //Wir gehen von einem Messbereich der Sensoren zwischen -100 und 120 Grad Celsius aus
		}
	}
	
	/*
	 * Thermometer sind entweder Boden- oder H�henthermometer.
	 * Temperatur wird in Grad Celsius angegeben.
	 */
	abstract class Thermometer {
		private Double temperatur;
		
		public Double getTemperatur() {
			return this.temperatur;
		}
		
		public void setTemp(Double temp) {
			temperatur = temp;
		}
		
		/*
		 * Zuf�lligen Test-Wert f�r Temperatur generieren. Zuf�llswerte k�nnen
		 * nicht-valide Temperaturen sein oder das Double Objekt auf null setzen. 
		 */
		public void setTestTemp() {
			Random r = new Random();
			temperatur = -100 + (120 -(-100)) * r.nextDouble();
			if (r.nextInt(100) >= 99)
				temperatur = 999.0;
			if (r.nextInt(100) >= 99)
				temperatur = null;
		}
	}
	
	public class Bodenthermometer extends Thermometer {
		public Bodenthermometer() {
			setTestTemp(); //Zuf�llige Temperatur generieren
		}
	}
	
	public class Hoehenthermometer extends Thermometer {
		public Hoehenthermometer() {
			setTestTemp(); //Zuf�llige Temperatur generieren
		}
	}
	
	/*
	 * Container-Klasse f�r Temperatur-Tupel von Wetterstationen
	 */
	public class Wetterdaten {
		private Double bodenTemp;
		private Double HoehenTemp;
		
		public Wetterdaten(Double bodenTemp, Double hoehenTemp) {
			this.bodenTemp = bodenTemp;
			this.HoehenTemp = hoehenTemp;
		}
		
		public Double getBodenTemp() {
			return this.bodenTemp;
		}
		
		public Double getHoehenTemp() {
			return this.HoehenTemp;
		}
	}
}	

