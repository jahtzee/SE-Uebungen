package aufgabe2;

import java.util.Set;
import java.util.HashSet;

public class Vorlesung {
   private String titel;
   
   public void setTitel(String value) {
      this.titel = value;
   }
   
   public String getTitel() {
      return this.titel;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Vorlesung ------------------------- Professor
    *           vorlesung        &gt;       professor
    * </pre>
    */
   private Professor professor;
   
   public void setProfessor(Professor value) {
      this.professor = value;
   }
   
   public Professor getProfessor() {
      return this.professor;
   }
   
   /**
    * <pre>
    *           0..*     1..*
    * Vorlesung ------------------------- Student
    *           vorlesung        &gt;       student
    * </pre>
    */
   private Set<Student> student;
   
   public Set<Student> getStudent() {
      if (this.student == null) {
         this.student = new HashSet<Student>();
      }
      return this.student;
   }
   
   public void print() {
	   System.out.println("Veranstaltung: " + this.getTitel() + "\n"
	   		+ "Dozent: " + this.getProfessor().toString() + "\n"
	   		+ "Studenten: ");
	   for (Student s : student) {
		   System.out.println(s.toString());
	   }
	   
   }
   
   }
