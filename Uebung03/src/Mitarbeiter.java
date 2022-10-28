/**
 * 
 */

/**
 * @author Jan Zimmer
 * last modified 28.10.2022
 */
public class Mitarbeiter {
private int personalNr;

	public Mitarbeiter (int nr) {
		this.setPersonalNr(nr);
	}
   
   public void setPersonalNr(int value) {
      this.personalNr = value;
   }
   
   public int getPersonalNr() {
      return this.personalNr;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Mitarbeiter ------------------------- Raum
    *           mitarbeiter        &gt;       raum
    * </pre>
    */
   private Raum raum;
   
   public void setRaum(Raum value) {
      this.raum = value;
   }
   
   public Raum getRaum() {
      return this.raum;
   }
   
   
}
