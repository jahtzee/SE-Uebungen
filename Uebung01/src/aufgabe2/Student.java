/**
 * @author Jan Zimmer
 * @date 21.10.2022
 */

package aufgabe2;

public class Student {
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
   
   private String Studentenk�rzel;
   
   public void setStudentenk�rzel(String value) {
      this.Studentenk�rzel = value;
   }
   
   public String getStudentenk�rzel() {
      return this.Studentenk�rzel;
   }
   
   public String getName() {
      return this.Nachname+","+this.Vorname;
   }
   
   public Student (String Vorname, String Nachname) {
      this.Vorname = Vorname;
      this.Nachname = Nachname;
   }
   
   
}
