import java.util.HashSet;
import java.util.Set;
/**
 * 
 */

/**
 * @author Jan Zimmer
 * last modified: 28.10.2022
 */
public class Raum {
private int raumNr;
   
	public Raum (int nr) {
		this.setRaumNr(nr);
	      if (this.mitarbeiter == null) {
	          this.mitarbeiter = new HashSet<Mitarbeiter>();
	       }
	}

   public void setRaumNr(int value) {
      this.raumNr = value;
   }
   
   public int getRaumNr() {
      return this.raumNr;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Raum ------------------------- Mitarbeiter
    *           raum        &lt;       mitarbeiter
    * </pre>
    */
   private Set<Mitarbeiter> mitarbeiter;
   
   public Set<Mitarbeiter> getMitarbeiter() {
      return this.mitarbeiter;
   }
   
   public void mitarbeiterZuweisen (Mitarbeiter m) {
	   m.setRaum(this);
	   this.mitarbeiter.add(m);
   }
   
   public void mitarbeiterEntfernen (Mitarbeiter m) {
	   mitarbeiter.removeIf(e -> (e.getPersonalNr() == m.getPersonalNr()));
   }
   
   public static void main (String[] args) {
	   // test
	   Raum h234 = new Raum(234);
	   Mitarbeiter amueller = new Mitarbeiter(1337);
	   Mitarbeiter jdoe = new Mitarbeiter(001);
	   h234.mitarbeiterZuweisen(amueller);
	   h234.mitarbeiterZuweisen(jdoe);
	   h234.mitarbeiterEntfernen(jdoe);
	   System.out.println("Mitarbeiter in Raum h234:");
	   for (Mitarbeiter m : h234.getMitarbeiter())
		   System.out.println(m.getPersonalNr());
	   // \test
   }
}