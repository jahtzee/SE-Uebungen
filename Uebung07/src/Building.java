/**
 * @author Jan Zimmer
 * last modified 26.12.2022
 * 
 * Building class demonstrating the template method pattern. Constructing a building follows an algorithm comprised of
 * 1) Laying a foundation
 * 2) Erecting pillars
 * 3) Constructing walls
 * 4) Constructing windows
 * While 1) and 4) are equivalent across all subclasses of Building, the implementation of 2) and 3) may vary.
 */
public abstract class Building {

	public final void constructFoundation() {
		System.out.println("Laying foundation");
	}
	public final void constructWindows() {
		System.out.println("Constructing windows");
	}

	protected abstract void constructPillars();
	protected abstract void constructWalls();
}

class GlassBuilding extends Building {
	@Override
	protected void constructPillars() {
		System.out.println("Constructing a steel beam frame for a glass building");
	}
	
	@Override
	protected void constructWalls() {
		System.out.println("Constructing glass facade");
	}
}

class WoodenBuilding extends Building {
	@Override
	protected void constructPillars() {
		System.out.println("Constructing wooden pillars");
	}
	
	@Override
	protected void constructWalls() {
		System.out.println("Constructing wooden facade");
	}
}

class StoneBuilding extends Building {
	@Override
	protected void constructPillars() {
		System.out.println("Constructing concrete pillars for stone building");
	}
	
	@Override
	protected void constructWalls() {
		System.out.println("Constructing brickwork");
	}
}
