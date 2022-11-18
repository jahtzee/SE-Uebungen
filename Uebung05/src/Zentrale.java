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
 * Diese Klasse enthält Logik zum Betrieb einer Wetterstationszentrale. Angehörige Nested-Classes umfassen
 * einzelne Wetterstationen, Thermometer und Wetterdaten.
 * 
 * Die main() Methode enthält ein Testszenario zum Errechnen der Durchschnittstemperatur über alle validen Datenpunkte der
 * zur Zentrale zugehörigen Wetterstationen, sowie Grenzwert-Tests für die isTempValid() und setErrorFlag() Methoden von Wetterstationen.
 * 
 * Zu Testzwecken werden den Thermometern bei der Instanziierung zufällige Temperaturen oder fehlerhafte Werte zugeordnet.
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
	 * Konstruktor für eine Wetterzentrale mit n Wetterstationen
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
		System.out.println("Die Durchschnittstemperatur beträgt: "+Math.round(ergebnis*100.0)/100.0+" Grad Celsius \n"
				+ "Es wurden "+counter+" Datenpunkte aus "+counter/2+" Wetterstationen berücksichtigt. \n"
				+ error+" Wetterstationen meldeten einen Fehler.");
	}
	/*
	 * Jede Wetterstation verfügt über genau zwei Thermometer. Wetterdaten können in Form
	 * von Werte-Paaren über getWetterdaten() abgefragt werden.
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
		 * der Sanity Check fehlschlägt.
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
			setErrorFlag(); //Erst prüfen, dann ausgeben
			return errorFlag;
		}
		
		public static boolean isTempValid(Double temp) {
			return (temp <= 120 && temp >= -100) ? true : false; //Wir gehen von einem Messbereich der Sensoren zwischen -100 und 120 Grad Celsius aus
		}
	}
	
	/*
	 * Thermometer sind entweder Boden- oder Höhenthermometer.
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
		 * Zufälligen Test-Wert für Temperatur generieren. Zufällswerte können
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
			setTestTemp(); //Zufällige Temperatur generieren
		}
	}
	
	public class Hoehenthermometer extends Thermometer {
		public Hoehenthermometer() {
			setTestTemp(); //Zufällige Temperatur generieren
		}
	}
	
	/*
	 * Container-Klasse für Temperatur-Tupel von Wetterstationen
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

