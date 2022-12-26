/**
 * @author Jan Zimmer last modified 26.12.2022
 * 
 *         Abstract employee class with nested worker and staff subclasses,
 *         meant to demonstrate the use of the template method pattern.
 *         Calculating the net salary, taxes and insurance is equivalent across
 *         subclasses, while calculation of the gross salary depends on the type
 *         of employee: Workers are payed on an hourly basis while staff is
 *         payed a fixed salary.
 */
public abstract class Employee {

	public static void main(String[] args) {
		/**
		 * Testcase
		 * 
		 * Expected output: 
		 * 
		 * John's gross salary: 
		 * 4000.0 
		 * John's net salary: 
		 * 2600.0 
		 * Jane's gross salary: 
		 * 2800.0 
		 * Jane's net salary: 
		 * 1820.0
		 */
		Worker JohnDoe = new Worker(25, 160);
		Staff JaneDoe = new Staff(2800);

		System.out.println("John's gross salary: ");
		System.out.println(JohnDoe.calculateGrossSalary());
		System.out.println("John's net salary: ");
		System.out.println(JohnDoe.calculateNetSalary());

		System.out.println("Jane's gross salary: ");
		System.out.println(JaneDoe.calculateGrossSalary());
		System.out.println("Jane's net salary: ");
		System.out.println(JaneDoe.calculateNetSalary());
	}

	protected abstract double calculateGrossSalary();

	public final double calculateInsurance() {
		double insurance = 0.15;
		return this.calculateGrossSalary() * insurance;
	}

	public final double calculateTaxes() {
		double taxes = 0.2;
		return this.calculateGrossSalary() * taxes;
	}

	public final double calculateNetSalary() {
		return this.calculateGrossSalary() - this.calculateTaxes() - this.calculateInsurance();
	}

}

class Worker extends Employee {
	private int hourlyWage;
	private int workingHours;

	Worker(int hourlyWage, int workingHours) {
		this.hourlyWage = hourlyWage;
		this.workingHours = workingHours;
	}

	@Override
	protected double calculateGrossSalary() {
		return hourlyWage * workingHours;
	}

}

class Staff extends Employee {
	private int fixedSalary;

	Staff(int fixedSalary) {
		this.fixedSalary = fixedSalary;
	}

	@Override
	protected double calculateGrossSalary() {
		return fixedSalary;
	}
}
