/*
 * @author Jan Zimmer
 * last modified 05.01.2023
 * 
 * Price calculation using a template method, since we're establishing a common algorithm (price calculation) which will be modified in part by subclasses.
 */
public abstract class Flooring {
	

	protected double pricePerSquareMeter;
	
	protected double baseratePrice;
	protected double surfaceArea;
	
	public static void main(String[] args) {
		Tiling tilingPricing = new Tiling(5,10,7);
		Parquet parquetPricing = new Parquet(8, 10, 0.5);
		System.out.println("Tiling: "+tilingPricing.calculatePrice()); //75.0
		System.out.println("Parquet: "+parquetPricing.calculatePrice()); //48.0
	}
	
	public Flooring (double baseRatePrice, double surfaceArea) {
		this.baseratePrice = baseRatePrice;
		this.surfaceArea = surfaceArea;
	}
	
	/*
	 * This is a algorithm common to all types of flooring for calculating their price. Price per m² differs between different types of flooring and is calculated using its own algorithm,
	 * which is why calculation of this field is delegated to subclasses of Flooring.
	 */
	public double calculatePrice() {
		return baseratePrice + surfaceArea * getPricePerSquareMeter();
	}
	
	public abstract double getPricePerSquareMeter();

}

class Tiling extends Flooring {
	
	public Tiling(double baseRatePrice, double surfaceArea, double fixedPricePerSquareMeter) {
		super(baseRatePrice, surfaceArea);
		this.pricePerSquareMeter = fixedPricePerSquareMeter;
	}
	
	@Override
	public double getPricePerSquareMeter() {
		// TODO Auto-generated method stub
		return pricePerSquareMeter;
	}
	
}


class Parquet extends Flooring {
	
	private double thickness;
	
	public Parquet(double baseRatePrice, double surfaceArea, double thickness) {
		super(baseRatePrice, surfaceArea);
		this.thickness = thickness;
	}
	
	@Override
	public double getPricePerSquareMeter() {
		// TODO Auto-generated method stub
		return baseratePrice * thickness;
	}
	
}

