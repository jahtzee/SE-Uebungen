/**
 * @author Jan Zimmer
 * last modified 06.12.2022
 * 
 * Finite-state machine 'Document' with test cases in main().
 */

public class Document {

	private State currentState;
	private boolean checkOkay = false;

	public static enum State {
		IN_PROCESSING, // in Bearbeitung
		DISCARDED, // Verworfen
		IN_CHECKING, // in Prüfung
		CLEARED, // Freigegeben
		PUBLISHED, // Veröffentlicht
		RETRACTED, // Zurückgezogen
		ARCHIVED // Archiviert
	}

	/**
	 * Main with multiple test cases
	 */
	public static void main(String[] args) {

		/**
		 * Perform various sane state transitions, including happy flow (new -> in_processing ->
		 * in_checking -> cleared -> published)
		 * 
		 * Expected output:
		 * 
		 * IN_PROCESSING
		 * IN_CHECKING
		 * IN_PROCESSING
		 * IN_PROCESSING
		 * CLEARED
		 * PUBLISHED
		 * RETRACTED
		 * IN_PROCESSING
		 * CLEARED
		 * ARCHIVED
		 */
		Document d1 = new Document();
		System.out.println(d1.getState()); //IN_PROCESSING
		d1.enterDocumentIntoChecking();
		System.out.println(d1.getState()); //IN_CHECKING
		d1.checkDocument();
		System.out.println(d1.getState()); //IN_PROCESSING
		d1.processDocument();
		System.out.println(d1.getState()); //IN_PROCESSING
		d1.setCheckOkayDEBUG(true);
		d1.enterDocumentIntoChecking();
		d1.checkDocument();
		System.out.println(d1.getState()); //CLEARED
		d1.publishDocument();
		System.out.println(d1.getState()); //PUBLISHED
		d1.retractDocument();
		System.out.println(d1.getState()); //RETRACTED
		d1.processDocument();
		System.out.println(d1.getState()); //IN_PROCESSING
		d1.enterDocumentIntoChecking();
		d1.checkDocument();
		System.out.println(d1.getState()); //CLEARED
		d1.archiveDocument();
		System.out.println(d1.getState()); //ARCHIVED

		System.out.println("\n==========================================\n");

		/*
		 * Attempt to perform illegal state transitions, which should not result in a state change,
		 * then discard the document from processing.
		 * 
		 * Expected output:
		 * IN_PROCESSING
		 * IN_PROCESSING
		 * IN_PROCESSING
		 * IN_PROCESSING
		 * IN_PROCESSING
		 * DISCARDED		
		 */
		Document d2 = new Document();
		System.out.println(d2.getState()); //IN_PROCESSING
		d2.checkDocument();
		System.out.println(d2.getState()); //IN_PROCESSING
		d2.publishDocument();
		System.out.println(d2.getState()); //IN_PROCESSING
		d2.retractDocument();
		System.out.println(d2.getState()); //IN_PROCESSING
		d2.archiveDocument();
		System.out.println(d2.getState()); //IN_PROCESSING
		d2.discardDocument();
		System.out.println(d2.getState()); //DISCARDED


	}

	public Document() {
		this.currentState = State.IN_PROCESSING;
	}

	private void setState(State state) {
		this.currentState = state;
	}

	/**
	 * For testing purposes only. checkOkay should be determined by checking logic
	 * in production.
	 */
	@SuppressWarnings("unused")
	public void setCheckOkayDEBUG(boolean b) {
		this.checkOkay = b;
	}

	public State getState() {
		return this.currentState;
	}

	public void processDocument() {
		if (this.getState() == State.IN_PROCESSING || this.getState() == State.RETRACTED) {
			/**
			 * Processing logic goes here
			 */
			this.setState(State.IN_PROCESSING);
		}
	}

	public void discardDocument() {
		if (this.getState() == State.IN_PROCESSING) {
			/**
			 * Discarding logic goes here
			 */
			this.setState(State.DISCARDED);
		}
	}

	public void enterDocumentIntoChecking() {
		if (this.getState() == State.IN_PROCESSING) {
			/**
			 * Enter checking logic goes here
			 */
			this.setState(State.IN_CHECKING);
		}
	}

	public void checkDocument() {
		if (this.getState() == State.IN_CHECKING) {
			/**
			 * Checking logic that determines checkOkay goes here
			 */
			if (this.checkOkay == true) {
				this.setState(State.CLEARED);
			} else {
				this.setState(State.IN_PROCESSING);
			}
		}
	}

	public void publishDocument() {
		if (this.getState() == State.CLEARED || this.getState() == State.RETRACTED) {
			/**
			 * Publishing logic goes here
			 */
			this.setState(State.PUBLISHED);
		}	
	}

	public void retractDocument() {
		if (this.getState() == State.PUBLISHED || this.getState() == State.CLEARED) {
			/**
			 * Retracting logic goes here
			 */
			this.setState(State.RETRACTED);
		}	
	}

	public void archiveDocument() {
		if (this.getState() == State.RETRACTED || this.getState() == State.CLEARED) {
			/**
			 * Archiving logic goes here
			 */
			this.setState(State.ARCHIVED);
		}	
	}

}
