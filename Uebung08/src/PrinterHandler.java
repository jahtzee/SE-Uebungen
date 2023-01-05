/**
 * @author Jan Zimmer
 * last modified 05.01.2023
 */
public class PrinterHandler extends DeviceHandler{

	/*
	 * Testing an implementation of our framework classes using a dummy Printer class as an example.
	 */
	public static void main(String[] args) {
		System.out.println("Drucker:");
		PrinterHandler printerHandler = new PrinterHandler();
		printerHandler.registerNewDevice("printer0001");
		for (Device d : printerHandler.getList()) {
			Printer p = (Printer) d;
			System.out.println(p.printerId);
		}
	}

	@Override
	protected Device createDevice(String printerId) {
		return new Printer(printerId);
	}
	
}
