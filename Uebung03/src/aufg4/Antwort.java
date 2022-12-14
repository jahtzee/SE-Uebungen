/**
 * 
 */
package aufg4;

/**
 * @author Jan Zimmer
 * last modified 28.10.2022
 */
public class Antwort {

	private int id;
	private Quizfrage frage;
	private String string;
	private String string2;
	
	public Antwort(int id, Quizfrage frage, String string, String string2) {
		this.id = id;
		this.string = string;
		this.string2 = string2;
		this.frage = frage;
	}
	
	public Boolean istAntwortRichtig() {
		return (this.getFrage().getRichtigeAntwort() == this) ? true : false;
	}

	public int getId() {
		return id;
	}

	public Quizfrage getFrage() {
		return frage;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getString2() {
		return string2;
	}

	public void setString2(String string2) {
		this.string2 = string2;
	}
	
	
}
