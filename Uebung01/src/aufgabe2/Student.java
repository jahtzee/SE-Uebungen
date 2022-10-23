package aufgabe2;
import java.util.HashSet;
import java.util.Set;

import java.util.ArrayList;

public class Student {
	
public Student(String vorname, String nachname, String matrikelnummer) {
		super();
		Vorname = vorname;
		Nachname = nachname;
		this.matrikelnummer = matrikelnummer;
		this.Studentenkürzel = vorname.substring(0, 2) + nachname.substring(0, 2) + matrikelnummer;
	}

/**
    * <pre>
    *           1..*     0..*
    * Student ------------------------- Vorlesung
    *           student        &lt;       vorlesung
    * </pre>
    */
   private Set<Vorlesung> vorlesung;
   
   public Set<Vorlesung> getVorlesung() {
      if (this.vorlesung == null) {
         this.vorlesung = new HashSet<Vorlesung>();
      }
      return this.vorlesung;
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
   
   private String Studentenkürzel;
   
   public void setStudentenkürzel(String value) {
      this.Studentenkürzel = value;
   }
   
   public String getStudentenkürzel() {
      return this.Studentenkürzel;
   }
   
   private Integer alter;
   
   public void setAlter(Integer value) {
      this.alter = value;
   }
   
   public Integer getAlter() {
      return this.alter;
   }
   
   private String matrikelnummer;
   
   private void setMatrikelnummer(String value) {
      this.matrikelnummer = value;
   }
   
   private String getMatrikelnummer() {
      return this.matrikelnummer;
   }
   
   private ArrayList<Double> noten;
   
   public void setNoten(ArrayList<Double> value) {
      this.noten = value;
   }
   
   public ArrayList<Double> getNoten() {
      return this.noten;
   }
   
   public String getName() {
      return this.Nachname+","+this.Vorname;
      }
   
   public Student (String Vorname, String Nachname) {
      this.Vorname = Vorname;
         this.Nachname = Nachname;
      }
   
   public double berechneDurchschnittsNote() {
      Double e = 0.0;
	   for (Double n : noten) {
    	  e+=n;
      }
	  return e/noten.size();
   }
   
   public void hinzufuegenNote(double note) {
      noten.add(note);
   }
   
   public void print() {
	   System.out.println(this.Studentenkürzel);
   }
   
   public String toString() {
	   return this.Vorname + " " + this.Nachname + " " + this.Studentenkürzel;
   }
   
   }
