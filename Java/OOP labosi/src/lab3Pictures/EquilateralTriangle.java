package lab3Pictures;

import hr.fer.oop.lab3.pic.Picture;

/**
 * Simple Drawable implementation that draws an equilateral triangle roughly 
 * centered in the given Picture object - the triangle's base is laid out 
 * horizontally and the Picutre's center should roughly correspond to the 
 * middle of the base's altitude.  
 * <p>This Drawable implementation uses a simple pixel-by-pixel algorithm. 
 * Simple roundings are used when converting from reals to integers, so edge 
 * effects are observable, especially when drawing small shapes.
 * <p>The triangle is defined by it's side (base) length. No methods to access 
 * the side length are provided, but the class does allow overriding of the 
 * internally kept side length value. Inheriting classes should take care to 
 * keep the side (base) length non-negative at all times (see the copy constructor).
 * 
 * The class supplies a main method with some simple functionality tests.
 *
 */
public class EquilateralTriangle implements Drawable {

	/*
	 * Inheriting classes may need access to base values to implement  
	 * different drawing algorithms, as well as possibly more complex 
	 * operations.
	 */
	protected double side;


	/**
	 * Constructor taking a side-length. The constructor will throw an 
	 * IllegalArgumentException if called with a negative argument.
	 * 
	 * @param side length of each of the triangle's sides.
	 * 
	 */
	public EquilateralTriangle(double side) {
		if(side >= 0) { 
			this.side = side;
		} else {
			throw new IllegalArgumentException("Can't instatiate triangles with a negative side length");
		}
	}
	
	/**
	 * The copy-constructor performs no checks on the original's (base) side
	 * length, so inheriting classes are expected to respect the non-negative 
	 * side length request.
	 *    
	 * @param original
	 */
	public EquilateralTriangle(EquilateralTriangle original) { 
		this.side = original.side;
	}

	/** 
	 * Draws a filled triangle centered in the picture by examining point-by-point whether 
	 * it meets the 3 line criteria
	 * 
	 */
	@Override
	public void drawOnPicture(Picture pic) {
		double sqt = Math.sqrt(3); //just to save some space... 
		// y0 = tan(120) x + C0 = - sqrt(3)*x + C0; y1 = tan(60) x + C1 = sqrt(3) + C1:
		double C0 = (pic.getHeight() + sqt * pic.getWidth()) / 2 - side * sqt / 4;
		double C1 = (pic.getHeight() - sqt * pic.getWidth()) / 2 - side * sqt / 4;;
		for(int y = 0; y < pic.getHeight(); y++) {
			for(int x = 0; x < pic.getWidth(); x++) { 
				if((y < pic.getHeight()/2 + side * sqt / 4) 
						&& (y > -sqt * x + C0) 
						&& (y > sqt * x + C1)
						) {					
					pic.turnPixelOn(x, y);
				}
			}
		}
	}

	public static void main(String[] args) { 
		int [][] tests = new int[][] { 
			{5, 5, 4, 4}
			, {5, 5, 3}
			, {5, 5, 2}
			, {5, 5, 1}
			, {5, 5, 0}
			, {4, 4, 4}
			, {4, 4, 5}
			, {4, 4, 6}
			, {4, 4, 7}
			, {4, 4, 1}
			, {4, 4, 2}
			, {4, 4, 3}
			, {4, 4, 4}
			, {4, 4, 5}
			, {4, 4, 2}
			, {4, 4, 4}
			, {100, 100, 50}
			, {150, 50, 0}
		};
		for(int[] test : tests) {
			System.out.println(new StringBuilder("Picture(w:")
					.append(Integer.toString(test[0]))
					.append(",h:")
					.append(Integer.toString(test[1]))
					.append("), Triangle(s:")
					.append(Integer.toString(test[2]))					
					.append("):")
					);
			try {
				Picture p = new Picture(test[0], test[1]);
				EquilateralTriangle t = new EquilateralTriangle(test[2]);
				t.drawOnPicture(p);
				p.renderImageToStream(System.out);
			}
			catch(Exception e) { 
				e.printStackTrace();
			}
		}
	}

}

