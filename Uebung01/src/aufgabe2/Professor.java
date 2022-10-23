package aufgabe2;

import java.util.Set;
import java.util.HashSet;

public class Professor {
	
	public static void main(String[] args) {
		Vorlesung swe = new Vorlesung();
		swe.setProfessor(new Professor("Adrian", "Müller", "IMST"));
		Student s1 = new Student("John", "Doe", "12356");
		s1.getVorlesung().add(swe);
		Student s2 = new Student("Jane", "Doe", "78910");
		s2.getVorlesung().add(swe);
		swe.getStudent().add(s1);
		swe.getStudent().add(s2);
		swe.print();
	}
	
   public Professor(String vorname, String nachname, String fachbereich) {
		super();
		Vorname = vorname;
		Nachname = nachname;
		Fachbereich = fachbereich;
	}

   private String Vorname;
   
   public void setVorname(String value) {
      this.Vorname = value;
   }
   
   public String getVorname() {
      return this.Vorname;
   }
   
   private String Nachname;
   
   public void setNachname(String value) {
      this.Nachname = value;
   }
   
   public String getNachname() {
      return this.Nachname;
   }
   
   private String Fachbereich;
   
   public void setFachbereich(String value) {
      this.Fachbereich = value;
   }
   
   public String getFachbereich() {
      return this.Fachbereich;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Professor ------------------------- Vorlesung
    *           professor        &lt;       vorlesung
    * </pre>
    */
   private Set<Vorlesung> vorlesung;
   
   public Set<Vorlesung> getVorlesung() {
      if (this.vorlesung == null) {
         this.vorlesung = new HashSet<Vorlesung>();
      }
      return this.vorlesung;
   }
   
   public void print() {
	   System.out.println(this.Vorname + " " + this.Nachname + " " + this.Fachbereich);
   }
   
   public String toString() {
	   return (this.Vorname + " " + this.Nachname + " " + this.Fachbereich);
   }
   
   }
